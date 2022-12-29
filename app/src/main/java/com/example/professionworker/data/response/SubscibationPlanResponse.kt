package com.example.professionworker.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize

data class SubscibationPlanResponse(
    @SerializedName("address") var address: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("open_time") var openTime: String? = null,
    @SerializedName("closed_time") var closedTime: String? = null,
    @SerializedName("username") var name: String? = null,
    @SerializedName("phone") var phone: String? = null,
// paramters added in profil requestd
    @SerializedName("lat") var lat: String? = null,
    @SerializedName("long") var long: String? = null,
 ) : Parcelable

