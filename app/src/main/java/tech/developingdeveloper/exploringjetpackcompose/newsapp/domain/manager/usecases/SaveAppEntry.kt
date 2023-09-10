package tech.developingdeveloper.exploringjetpackcompose.newsapp.domain.manager.usecases

import tech.developingdeveloper.exploringjetpackcompose.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager,
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}