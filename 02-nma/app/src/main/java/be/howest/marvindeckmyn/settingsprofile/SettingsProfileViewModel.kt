package be.howest.marvindeckmyn.settingsprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.Genres
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import be.howest.marvindeckmyn.network.UserInfo
import kotlinx.coroutines.launch
import java.lang.Exception

class SettingsProfileViewModel(dataSource: UserDatabaseDao) : ViewModel() {

    val database = dataSource

    private val _producer = MutableLiveData<List<Producers>>()
    private val _genres = MutableLiveData<List<Genres>>()

    val producer: LiveData<List<Producers>>
        get() = _producer

    val genres: LiveData<List<Genres>>
        get() = _genres

    init {
        getMyUser()
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                _genres.value = UniversalBeatsApi.retrofitService.getGenres()
            } catch (e: Exception) {
                _genres.value = ArrayList()
            }
        }
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

    fun updateUserInfo(userInfo: UserInfo) {
        viewModelScope.launch {
            try {
                UniversalBeatsApi.retrofitService.updateUserInfo(userInfo)
            } catch (e: Exception) {
                Log.d("exception", e.toString())
            }
        }
    }
}