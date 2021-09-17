package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Producers (
        val id: Int,
        val username: String,
        val name: String? = null,
        val avatar: String,
        val background: String,
        val aboutme: String? = null,
        val genre: String? = null,
        val twitter: String? = null,
        val instagram: String? = null,
        val youtube: String? = null,
        val email: String
) : Parcelable