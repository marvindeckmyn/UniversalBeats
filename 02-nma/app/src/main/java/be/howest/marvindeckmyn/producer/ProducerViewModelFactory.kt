package be.howest.marvindeckmyn.producer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.network.Producers

class ProducerViewModelFactory(
    private val producer: Producers,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProducerViewModel::class.java)) {
            return ProducerViewModel(producer, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}