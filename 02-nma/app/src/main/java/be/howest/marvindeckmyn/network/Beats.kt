package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beats (
    val id: Int,
    val name: String,
    val producer_id: String,
    val cover: String,
    val genre: String,
    val tag: String? = null,
    val bpm: String,
    val mood: String,
    val price: String,
    val audio: String
) : Parcelable