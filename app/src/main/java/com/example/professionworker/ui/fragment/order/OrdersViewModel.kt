package com.example.professionworker.ui.fragment.order

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.professionworker.R
import com.example.professionworker.base.BaseViewModel
import com.example.professionworker.data.params.*
import com.example.professionworker.domain.response.OrderdResponse
import com.example.professionworker.domain.response.OrdersItem
import com.example.professionworker.domain.response.ReviewsResponse
import com.example.professionworker.domain.OrdersUseCase
import com.example.professionworker.domain.ReviewsUseCase
import com.example.professionworker.ui.fragments.order.OrdersAction
import com.example.professionworker.util.NetworkConnectivity
import com.example.professionworker.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OrdersViewModel
@Inject constructor(app: Application, val useCase: OrdersUseCase, val usecaseReviews : ReviewsUseCase) :
    BaseViewModel<OrdersAction>(app) {

  var   orderId :String =""
  var  data: OrdersItem? = null
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
    }   fun getNewOrders(state: String) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {

            produce(OrdersAction.ShowLoading(true))
            useCase.invoke(
                viewModelScope,
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
 fun showOrderAction(
        params: OrderActionsParams
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
                        produce(OrdersAction.ShowOrderActions(res.data.message as String,params))
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

 



