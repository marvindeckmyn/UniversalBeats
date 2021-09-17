package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Avatar(
        val id: Int,
        val avatar: String
) : Parcelable
