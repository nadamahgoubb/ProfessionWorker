package com.horizon.professionworker.data.response

import com.google.gson.annotations.SerializedName

data class GoalResponse(
    @SerializedName("id"                         ) var id                       : Int?                   = null,
    @SerializedName("name"                       ) var name                     : String?                = null,
    @SerializedName("content"                       ) var content                     : String?                = null,

)
