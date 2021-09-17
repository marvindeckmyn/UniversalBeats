package be.howest.marvindeckmyn.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.network.Beats
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _beats = MutableLiveData<List<Beats>>()

    val beats: LiveData<List<Beats>>
        get() = _beats

    private val _navigateToSelectedBeat = MutableLiveData<Beats?>()
    val navigateToSelectedBeat: LiveData<Beats?>
        get() = _navigateToSelectedBeat

    private val _producers = MutableLiveData<List<Producers>>()

    val producers: LiveData<List<Producers>>
        get() = _producers

    private val _navigateToSelectedProducer = MutableLiveData<Producers?>()
    val navigateToSelectedProducer: LiveData<Producers?>
        get() = _navigateToSelectedProducer

    init {
        getBeats()
        getProducers()
    }

    private fun getBeats() {
        viewModelScope.launch {
            try {
                _beats.value = UniversalBeatsApi.retrofitService.getBeats().reversed().subList(0, 4)
            } catch (e: Exception) {
                _beats.value = ArrayList()
            }
        }
    }

    private fun getProducers() {
        viewModelScope.launch {
            try {
                _producers.value = UniversalBeatsApi.retrofitService.getProducers().reversed().subList(0, 4)
            } catch (e: Exception) {
                _producers.value = ArrayList()
            }
        }
    }

    fun displayBeat(beat: Beats) {
        _navigateToSelectedBeat.value = beat
    }

    fun displayBeatComplete() {
        _navigateToSelectedBeat.value = null
    }

    fun displayProducer(producer: Producers) {
        _navigateToSelectedProducer.value = producer
    }

    fun displayProducerComplete() {
        _navigateToSelectedProducer.value = null
    }
}