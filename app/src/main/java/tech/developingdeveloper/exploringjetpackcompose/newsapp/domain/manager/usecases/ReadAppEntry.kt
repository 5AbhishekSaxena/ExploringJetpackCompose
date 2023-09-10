package tech.developingdeveloper.exploringjetpackcompose.newsapp.domain.manager.usecases

import kotlinx.coroutines.flow.Flow
import tech.developingdeveloper.exploringjetpackcompose.newsapp.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager,
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}