package com.example.professionworker.data.params
data class GetOrderParam(var order_status:String)
data class OrderDetailsParam(var order_id:String)
data class AddReviewsParam(
    val provider_id: String? = null,
    val  user_id: String? = null,
    val order_id: String? = null,
    val rate: String? = null,
    val comment: String? = null,)


data class CancelOrderParam(
    val order_id: String = "",
    val order_status: String = "",)

data class ComplainOrderParam(
    val order_id: String = "",
    val complaint: String = "",)

data class OrderActionsParams(
    val order_id: String = "",
    val status_id: String = "",
    val action_type: Int  ,


    )

data class PaymentModel(

    var id: Int?    = null,
    var title: String?    = null,
    var logo: Int? = null,
    var selected: Boolean?    = false,

    )
