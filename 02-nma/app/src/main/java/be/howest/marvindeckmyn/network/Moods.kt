package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Moods (
    val id: Int,
    val name: String
) : Parcelable