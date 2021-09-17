package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
        val id: Int,
        val username: String,
        val name: String,
        val email: String,
        val aboutme: String,
        val genre: String,
        val twitter: String,
        val instagram: String,
        val youtube: String
) : Parcelable
