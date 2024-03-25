package com.dot_jo.professionworker.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewsResponse(
    @SerializedName("reviews" ) var reviews : ArrayList<Reviews> = arrayListOf()
) : Parcelable
@Parcelize
data class Reviews (
    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("provider_id"    ) var providerId    : Int?    = null,
    @SerializedName("provider_name"  ) var providerName  : String? = null,
    @SerializedName("provider_photo" ) var providerPhoto : String? = null,
    @SerializedName("user_id"        ) var userId        : Int?    = null,
    @SerializedName("user_name"      ) var userName      : String? = null,
    @SerializedName("user_photo"     ) var userPhoto     : String? = null,
    @SerializedName("order_id"       ) var orderId       : Int?    = null,
    @SerializedName("rate"           ) var rate          : Double? = null,
    @SerializedName("comment"        ) var comment       : String? = null,
    @SerializedName("created_at"     ) var createdAt     : String? = null

) : Parcelable
