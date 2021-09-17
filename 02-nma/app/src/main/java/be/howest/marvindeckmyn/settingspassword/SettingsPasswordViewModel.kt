package be.howest.marvindeckmyn.settingspassword

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.NewPassword
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class SettingsPasswordViewModel(dataSource: UserDatabaseDao) : ViewModel() {

    val database = dataSource

    private val _producer = MutableLiveData<List<Producers>>()

    val producer: LiveData<List<Producers>>
        get() = _producer

    init {
        getMyUser()
    }

    private fun getMyUser() {
        val myUsername = database.getMyUser().username

        getProducerByUsername(myUsername)
    }

    private fun getProducerByUsername(username: String) {
        viewModelScope.launch {
            try {
                _producer.value = UniversalBeatsApi.retrofitService.getProducerByUsername(username)
            } catch (e: Exception) {
                _producer.value = ArrayList()
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            try {
                database.clear()
            } catch (e: Exception) {
                Log.d("exception", e.toString())
            }
        }
    }

    fun changePassword(newPassword: NewPassword) {
        viewModelScope.launch {
            try {
                UniversalBeatsApi.retrofitService.changePassword(newPassword)
            } catch (e: Exception) {
                Log.d("exception", e.toString())
            }
        }
    }
}