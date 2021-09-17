package be.howest.marvindeckmyn.beat

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.network.Beats
import java.lang.IllegalArgumentException

class BeatViewModelFactory(
        private val beat: Beats,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeatViewModel::class.java)) {
            return BeatViewModel(beat, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}