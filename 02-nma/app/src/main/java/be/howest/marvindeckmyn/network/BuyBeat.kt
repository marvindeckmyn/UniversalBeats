package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BuyBeat(
    val notifiable_id: Int?,
    val data: String?
) : Parcelable
