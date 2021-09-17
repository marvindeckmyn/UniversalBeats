package be.howest.marvindeckmyn.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewPassword(
        val id: Int,
        val oldpassword: String,
        val newpassword: String,
        val confirmpassword: String
) : Parcelable
