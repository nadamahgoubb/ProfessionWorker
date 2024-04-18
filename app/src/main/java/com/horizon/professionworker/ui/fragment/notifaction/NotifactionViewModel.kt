package com.horizon.professionworker.ui.fragment.notifaction

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.horizon.professionworker.R
import com.horizon.professionworker.base.BaseViewModel
import com.horizon.professionworker.data.response.NotificationResponse
import com.horizon.professionworker.domain.NotifactionUseCase
import com.horizon.professionworker.ui.fragments.order.NotifactionAction
 import com.horizon.professionworker.util.NetworkConnectivity
import com.horizon.professionworker.util.Resource

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

 



