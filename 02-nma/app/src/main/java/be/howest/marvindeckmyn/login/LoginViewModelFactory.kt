package be.howest.marvindeckmyn.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class LoginViewModelFactory(private val dataSource: UserDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}