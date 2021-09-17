package be.howest.marvindeckmyn.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.MyUser
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import be.howest.marvindeckmyn.network.User
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class LoginViewModel(dataSource: UserDatabaseDao) : ViewModel() {

    val database = dataSource

    var loginSuccessCheck = "false"

    private val _producer = MutableLiveData<List<Producers>>()

    val producer: LiveData<List<Producers>>
        get() = _producer

    fun login(user: User) {
        viewModelScope.launch {
            try {
                val login = UniversalBeatsApi.retrofitService.login(user)
                loginSuccessCheck = if (login.headers().value(4) == "1") {
                    "true"
                } else {
                    "false"
                }
            } catch (e: Exception){
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