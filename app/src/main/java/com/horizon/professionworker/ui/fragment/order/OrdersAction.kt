package com.horizon.professionworker.ui.fragments.order

import com.horizon.professionworker.base.Action
import com.horizon.professionworker.data.params.OrderActionsParams
import com.horizon.professionworker.data.response.OrderdResponse
import com.horizon.professionworker.data.response.OrdersItem
import com.horizon.professionworker.data.response.ReviewsResponse

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
