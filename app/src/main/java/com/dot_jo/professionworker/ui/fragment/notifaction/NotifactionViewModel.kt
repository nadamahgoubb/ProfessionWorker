package com.dot_jo.professionworker.ui.fragment.notifaction

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.base.BaseViewModel
import com.dot_jo.professionworker.data.response.NotificationResponse
import com.dot_jo.professionworker.domain.NotifactionUseCase
import com.dot_jo.professionworker.ui.fragments.order.NotifactionAction
 import com.dot_jo.professionworker.util.NetworkConnectivity
import com.dot_jo.professionworker.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NotifactionViewModel
@Inject constructor(app: Application, val useCase: NotifactionUseCase ) :
    BaseViewModel<NotifactionAction>(app) {

    fun getNotifaction(   ) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(NotifactionAction.ShowLoading(true))
            useCase.invoke(
                viewModelScope
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(NotifactionAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(NotifactionAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(NotifactionAction.ShowNotifaction(res.data.data as NotificationResponse))

                    }
                }
            }
        } else {
            produce(NotifactionAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

}

 



