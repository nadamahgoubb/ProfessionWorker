package com.dot_jo.professionworker.ui.fragments.order

import com.dot_jo.professionworker.base.Action
import com.dot_jo.professionworker.data.params.OrderActionsParams
import com.dot_jo.professionworker.data.response.OrderdResponse
import com.dot_jo.professionworker.data.response.OrdersItem
import com.dot_jo.professionworker.data.response.ReviewsResponse

sealed class OrdersAction() : Action {
    data class  ShowOrders(val data: OrderdResponse, val state: String): OrdersAction()
     data class ShowLoading(val show: Boolean) : OrdersAction()
    data class ShowFailureMsg(val message: String?) : OrdersAction()
    data class ShowReviews(val data: ReviewsResponse?) : OrdersAction()
    data class ShowCanceledOrder(val message: String) : OrdersAction()
    data class ShowOrderActions(val message: String, val params: OrderActionsParams) : OrdersAction()
    data class ShowOrderDetails( val data: OrdersItem) : OrdersAction()
    data class ShowComplainedOrder(val message: String) : OrdersAction()
}
