package be.howest.marvindeckmyn.profile

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.Beats
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel(dataSource: UserDatabaseDao) : ViewModel() {

    val database = dataSource

    private val _producer = MutableLiveData<List<Producers>>()
    private val _beats = MutableLiveData<List<Beats>>()
    private val _navigateToSelectedBeat = MutableLiveData<Beats?>()

    val producer: LiveData<List<Producers>>
        get() = _producer

    val beats: LiveData<List<Beats>>
        get() = _beats

    val navigateToSelectedBeat: LiveData<Beats?>
        get() = _navigateToSelectedBeat

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
                getBeatsOfProducers()
            } catch (e: Exception) {
                _producer.value = ArrayList()
            }
        }
    }

    private fun getBeatsOfProducers() {
        viewModelScope.launch {
            try {
                val producerId = _producer.value?.get(0)?.id
                _beats.value = producerId?.let {
                    UniversalBeatsApi.retrofitService.getBeatsByProducerId(
                        it
                    )
                }
            } catch (e: Exception) {
                _beats.value = ArrayList()
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

    fun displayBeat(beat: Beats) {
        _navigateToSelectedBeat.value = beat
    }

    fun displayBeatComplete() {
        _navigateToSelectedBeat.value = null
    }
}