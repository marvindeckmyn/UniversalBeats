package be.howest.marvindeckmyn.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.MyUser
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.Genres
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.RegisterUser
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel(dataSource: UserDatabaseDao) : ViewModel() {

    private val _producers = MutableLiveData<List<Producers>>()
    private val _genres = MutableLiveData<List<Genres>>()

    val producers: LiveData<List<Producers>>
        get() = _producers

    val genres: LiveData<List<Genres>>
        get() = _genres

    val database = dataSource

    var registerSuccessCheck = "false"

    private val _producer = MutableLiveData<List<Producers>>()

    val producer: LiveData<List<Producers>>
        get() = _producer

    init {
        getProducers()
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

    private fun getProducers() {
        viewModelScope.launch {
            try {
                _producers.value = UniversalBeatsApi.retrofitService.getProducers()
            } catch (e: Exception) {
                _producers.value = ArrayList()
            }
        }
    }

    fun register(registerUser: RegisterUser) {
        viewModelScope.launch {
            try {
                val register = UniversalBeatsApi.retrofitService.register(registerUser)
                registerSuccessCheck = if (register.headers().value(4) == "1") {
                    "true"
                } else {
                    "false"
                }
            } catch (e: Exception) {
                Log.d("exception", e.toString())
            }
        }
    }

    fun getProducerByUsername(username: String) {
        viewModelScope.launch {
            try {
                clear()
                _producer.value = UniversalBeatsApi.retrofitService.getProducerByUsername(username)

                val id = _producer.value!![0].id
                val myUsername = _producer.value!![0].username

                val user = MyUser(id, myUsername)

                insert(user)
            } catch (e: Exception) {
                _producer.value = ArrayList()
            }
        }
    }

    private suspend fun insert(user: MyUser) {
        database.insert(user)
    }

    private suspend fun clear() {
        database.clear()
    }
}