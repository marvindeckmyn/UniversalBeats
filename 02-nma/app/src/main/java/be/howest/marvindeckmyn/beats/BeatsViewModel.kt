package be.howest.marvindeckmyn.beats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.marvindeckmyn.network.Beats
import be.howest.marvindeckmyn.network.UniversalBeatsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class BeatsViewModel : ViewModel() {

    private val _beats = MutableLiveData<List<Beats>>()

    val beats: LiveData<List<Beats>>
        get() = _beats

    private val _navigateToSelectedBeat = MutableLiveData<Beats?>()
    val navigateToSelectedBeat: LiveData<Beats?>
        get() = _navigateToSelectedBeat

    init {
        getBeats()
    }

    private fun getBeats() {
        viewModelScope.launch {
            try {
                _beats.value = UniversalBeatsApi.retrofitService.getBeats().reversed()
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