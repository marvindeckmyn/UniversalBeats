package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SoldBeats(
    val data: String,
    val created_at: String
) : Parcelable
