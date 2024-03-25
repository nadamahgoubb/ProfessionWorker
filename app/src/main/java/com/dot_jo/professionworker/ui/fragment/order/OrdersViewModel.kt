package com.dot_jo.professionworker.ui.fragment.order

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.base.BaseViewModel
import com.dot_jo.professionworker.data.params.*
import com.dot_jo.professionworker.data.repo.PrefsHelper
import com.dot_jo.professionworker.data.response.OrderdResponse
import com.dot_jo.professionworker.data.response.OrdersItem
import com.dot_jo.professionworker.data.response.ReviewsResponse
import com.dot_jo.professionworker.domain.OrdersUseCase
import com.dot_jo.professionworker.domain.ReviewsUseCase
import com.dot_jo.professionworker.ui.fragments.order.OrdersAction
import com.dot_jo.professionworker.util.NetworkConnectivity
import com.dot_jo.professionworker.util.Resource
import com.dot_jo.professionworker.util.fcm.FcmParam
import com.dot_jo.professionworker.util.fcm.FcmUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OrdersViewModel
@Inject constructor(app: Application, val useCase: OrdersUseCase, val usecaseReviews : ReviewsUseCase, val useCaseFcm : FcmUseCase) :
    BaseViewModel<OrdersAction>(app) {

  var   orderId :String =""
  var  data: OrdersItem? = null
init {
    updateFcmToken()
}
    fun updateFcmToken() {
        if (PrefsHelper.getFcmToken().isNullOrEmpty()) useCaseFcm.generateFcmToken()
        else useCaseFcm.sendFcmTokenToServer(FcmParam(PrefsHelper.getFcmToken()))


    }
    fun getOrders(state: String) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {

            produce(OrdersAction.ShowLoading(true))
            useCase.invoke(
                viewModelScope, GetOrderParam(
                   state
                )
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(OrdersAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(OrdersAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                         produce(OrdersAction.ShowOrders(res.data.data as OrderdResponse, state))

                    }
                }
            }
        } else {
            produce(OrdersAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun getNewOrders(state: String) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {

            produce(OrdersAction.ShowLoading(true))
            useCase.invoke(
                viewModelScope
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(OrdersAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(OrdersAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                         produce(OrdersAction.ShowOrders(res.data.data as OrderdResponse, state))

                    }
                }
            }
        } else {
            produce(OrdersAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun getReviews(
        params: AddReviewsParam
    ) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {

            produce(OrdersAction.ShowLoading(true))
            usecaseReviews.invoke(
                viewModelScope
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(OrdersAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(OrdersAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(OrdersAction.ShowReviews(res.data.data as ReviewsResponse))
                    }
                }
            }
        } else {
            produce(OrdersAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }
 fun showOrderAction(params: OrderActionsParams) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {

            produce(OrdersAction.ShowLoading(true))
            useCase.invoke(
                viewModelScope, params
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(OrdersAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(OrdersAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(OrdersAction.ShowOrderActions(res.data.message as String,params))
                    }
                }
            }
        } else {
            produce(OrdersAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }
 fun getOrderDetails(params: String) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {

            produce(OrdersAction.ShowLoading(true))
            useCase.invoke(
                viewModelScope,OrderDetailsParam
                        (params)
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(OrdersAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(OrdersAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(OrdersAction.ShowOrderDetails(res.data.data   as  OrdersItem))
                    }
                }
            }
        } else {
            produce(OrdersAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun complainOrder(
        params: ComplainOrderParam
    ) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {

            produce(OrdersAction.ShowLoading(true))
            useCase.invoke(
                viewModelScope, params
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(OrdersAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(OrdersAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(OrdersAction.ShowComplainedOrder(res.data.message as String))
                    }
                }
            }
        } else {
            produce(OrdersAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

}

 



