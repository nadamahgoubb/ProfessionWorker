package com.example.professionworker.ui.fragments.order

import com.example.professionworker.base.Action
import com.example.professionworker.domain.response.OrderdResponse
import com.example.professionworker.domain.response.OrdersItem
import com.example.professionworker.domain.response.ReviewsResponse

sealed class OrdersAction() : Action {
    data class  ShowOrders(val data: OrderdResponse, val state: String): OrdersAction()
    data class  ShowOrderDetails(val data: OrdersItem): OrdersAction()
    data class ShowLoading(val show: Boolean) : OrdersAction()
    data class ShowFailureMsg(val message: String?) : OrdersAction()
    data class ShowReviews(val data: ReviewsResponse?) : OrdersAction()
    data class ShowCanceledOrder(val message: String) : OrdersAction()
    data class ShowOrderActions(val message: String) : OrdersAction()
    data class ShowComplainedOrder(val message: String) : OrdersAction()
}