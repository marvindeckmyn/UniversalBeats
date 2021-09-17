package be.howest.marvindeckmyn.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class ProfileViewModelFactory(private val dataSource: UserDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}