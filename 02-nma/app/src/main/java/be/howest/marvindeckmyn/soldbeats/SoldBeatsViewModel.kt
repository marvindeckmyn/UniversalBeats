package be.howest.marvindeckmyn.soldbeats

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.SoldBeats
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class SoldBeatsViewModel(dataSource: UserDatabaseDao) : ViewModel() {

    val database = dataSource

    private val _soldBeats = MutableLiveData<List<SoldBeats>>()

    val soldBeats: LiveData<List<SoldBeats>>
        get() = _soldBeats

    init {
        getSoldBeats()
    }

    private fun getSoldBeats() {
        viewModelScope.launch {
            try {
                val id = database.getMyUser().id
                _soldBeats.value = UniversalBeatsApi.retrofitService.getSoldBeats(id).reversed()
            } catch (e: Exception) {
                _soldBeats.value = ArrayList()
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

}