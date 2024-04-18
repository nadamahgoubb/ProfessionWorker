package com.horizon.professionworker.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServicesItemsResponse(

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("service_id" ) var serviceId : String?    = null,
    @SerializedName("icon"       ) var icon      : String? = null,
    @SerializedName("active"     ) var active    : Int?    = null,
    @SerializedName("name"       ) var name      : String? = null,

    var choosen: Boolean= false

) : Parcelable

@Parcelize
data class NationalitiesResponse(
    @SerializedName("nationalities" ) var nationalities : ArrayList<NationalitiesItemResponse> = arrayListOf()


) : Parcelable
@Parcelize
data class NationalitiesItemResponse(

    @SerializedName("id"         ) var id        : String?    = null,

    @SerializedName("name"       ) var name      : String? = null,
    var choosen: Boolean= false


) : Parcelable

@Parcelize
data class BanksResponse(
    @SerializedName("banks" ) var banks : ArrayList<BankItemResponse> = arrayListOf()


) : Parcelable
@Parcelize
data class BankItemResponse(
    @SerializedName("id"         ) var id        : String?    = null,
    @SerializedName("active"     ) var active    : Int?    = null,
    @SerializedName("name"       ) var name      : String? = null,
    var choosen: Boolean= false


    ) : Parcelable


data class SubServiceItemsResponse(

    @SerializedName("id"                ) var id              : Int?    = null,
    @SerializedName("service_id"                ) var service_id              : String?    = null,
    @SerializedName("name"              ) var name            : String? = null,
    @SerializedName("icon"       ) var icon      : String? = null,
    @SerializedName("active"     ) var active    : Int?    = null,

    var choosen: Boolean= false

)
