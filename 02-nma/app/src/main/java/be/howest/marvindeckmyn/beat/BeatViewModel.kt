package be.howest.marvindeckmyn.beat

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.network.*
import kotlinx.coroutines.launch
import java.lang.Exception

class BeatViewModel( beat: Beats, app: Application) : AndroidViewModel(app) {

    private val _selectedBeat = MutableLiveData<Beats>()
    private val _producer = MutableLiveData<List<Producers>>()
    private val _navigateToSelectedProducer = MutableLiveData<Producers?>()
    private val _plays = MutableLiveData<List<Plays>>()

    val selectedBeat: LiveData<Beats>
        get() = _selectedBeat

    val producer: LiveData<List<Producers>>
        get() = _producer

    val navigateToSelectedProducer: LiveData<Producers?>
        get() = _navigateToSelectedProducer

    val plays: LiveData<List<Plays>>
        get() = _plays

    init {
        _selectedBeat.value = beat
        getProducer()
        registerPlay(_selectedBeat.value!!.id)
        getPlays(_selectedBeat.value!!.id)
    }

    private fun registerPlay(beatId: Int) {
        viewModelScope.launch {
            try {
                val play = Plays(beatId)
                UniversalBeatsApi.retrofitService.registerPlay(play)
            } catch (e: Exception) {
                Log.d("exception", e.toString())
            }
        }
    }

    fun buyBeat(buyBeat: BuyBeat) {
        viewModelScope.launch {
            try {
                UniversalBeatsApi.retrofitService.buyBeat(buyBeat)
            } catch (e: Exception) {
                Log.d("exception", e.toString())
            }
        }
    }

    private fun getPlays(beatId: Int) {
        viewModelScope.launch {
            try{
                _plays.value = UniversalBeatsApi.retrofitService.getPlays(beatId)
            } catch (e: Exception) {
                _plays.value = ArrayList()
            }
        }
    }

    private fun getProducer() {
        viewModelScope.launch {
            try {
                val producerId = _selectedBeat.value?.producer_id
                if (producerId != null) {
                    _producer.value = UniversalBeatsApi.retrofitService.getProducer(producerId.toInt())
                }
            } catch (e: Exception) {
                _producer.value = ArrayList()
            }
        }
    }

    fun displayProducer(producer: Producers) {
        _navigateToSelectedProducer.value = producer
    }

    fun displayProducerComplete() {
        _navigateToSelectedProducer.value = null
    }
}