package be.howest.marvindeckmyn.settingsavatar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class SettingsAvatarViewModelFactory(private val dataSource: UserDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsAvatarViewModel::class.java)) {
            return SettingsAvatarViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}