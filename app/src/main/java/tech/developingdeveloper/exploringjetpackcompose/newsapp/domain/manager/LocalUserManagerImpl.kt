package tech.developingdeveloper.exploringjetpackcompose.newsapp.domain.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.developingdeveloper.exploringjetpackcompose.newsapp.util.Constants

class LocalUserManagerImpl(context: Context) : LocalUserManager {

    private val dataStore: DataStore<Preferences> = context.dataStore

    override suspend fun saveAppEntry() {
        dataStore.edit { pref -> pref[APP_ENTRY] = true }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data.map { it[APP_ENTRY] ?: false }
    }

    companion object {
        private val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_SETTINGS)
