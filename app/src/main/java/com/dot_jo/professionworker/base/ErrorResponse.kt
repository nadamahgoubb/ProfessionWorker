package com.dot_jo.professionworker.base

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorResponse(
    @SerializedName("message")
    var Error: String = "",

) : Parcelable
