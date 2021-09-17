package be.howest.marvindeckmyn.soldbeats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class SoldBeatsViewModelFactory(private val dataSource: UserDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SoldBeatsViewModel::class.java)) {
            return SoldBeatsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}