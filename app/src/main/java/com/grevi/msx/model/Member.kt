package com.grevi.msx.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Member(
    @SerializedName("name") val name : String,
    @SerializedName("hobby") val hobby : String,
    @SerializedName("age") val age : Int,
    @SerializedName("address") val address : String
) : Parcelable