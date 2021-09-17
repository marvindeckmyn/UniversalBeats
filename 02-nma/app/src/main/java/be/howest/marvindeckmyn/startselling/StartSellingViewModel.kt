package be.howest.marvindeckmyn.startselling

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class StartSellingViewModel(dataSource: UserDatabaseDao) : ViewModel() {

    val database = dataSource

    private val _producer = MutableLiveData<List<Producers>>()
    private val _genres = MutableLiveData<List<Genres>>()
    private val _moods = MutableLiveData<List<Moods>>()
    private val _beats = MutableLiveData<List<Beats>>()

    val producer: LiveData<List<Producers>>
        get() = _producer

    val genres: LiveData<List<Genres>>
        get() = _genres

    val moods: LiveData<List<Moods>>
        get() = _moods

    val beats: LiveData<List<Beats>>
        get() = _beats

    init {
        getGenres()
        getMoods()
        getBeats()
        getMyUser()
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

    private fun getMoods() {
        viewModelScope.launch {
            try {
                _moods.value = UniversalBeatsApi.retrofitService.getMoods()
            } catch (e: Exception) {
                _moods.value = ArrayList()
            }
        }
    }

    private fun getBeats() {
        viewModelScope.launch {
            try {
                _beats.value = UniversalBeatsApi.retrofitService.getBeats()
            } catch (e: Exception) {
                _beats.value = ArrayList()
            }
        }
    }

    fun postBeat(beat: Beats) {
        viewModelScope.launch {
            try {
                UniversalBeatsApi.retrofitService.postBeat(beat)
            } catch (e: Exception) {
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
            } catch (e: java.lang.Exception) {
                _producer.value = ArrayList()
            }
        }
    }
}