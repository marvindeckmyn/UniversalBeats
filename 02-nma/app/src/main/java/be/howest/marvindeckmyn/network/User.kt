package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val username: String,
    val password: String
) : Parcelable