package be.howest.marvindeckmyn.producer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.database.UserDatabaseDao
import be.howest.marvindeckmyn.network.Beats
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class ProducerViewModel( producer: Producers, app: Application) : AndroidViewModel(app) {

    private val _selectedProducer = MutableLiveData<Producers>()
    private val _beats = MutableLiveData<List<Beats>>()
    private val _navigateToSelectedBeat = MutableLiveData<Beats?>()

    val selectedProducer: LiveData<Producers>
        get() = _selectedProducer

    val beats: LiveData<List<Beats>>
        get() = _beats

    val navigateToSelectedBeat: LiveData<Beats?>
        get() = _navigateToSelectedBeat

    init {
        _selectedProducer.value = producer
        getBeatsOfProducers()
    }

    private fun getBeatsOfProducers() {
        viewModelScope.launch {
            try {
                val producerId = _selectedProducer.value?.id
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

    fun displayBeat(beat: Beats) {
        _navigateToSelectedBeat.value = beat
    }

    fun displayBeatComplete() {
        _navigateToSelectedBeat.value = null
    }
}