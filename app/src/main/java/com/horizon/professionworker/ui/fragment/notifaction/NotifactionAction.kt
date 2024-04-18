package com.horizon.professionworker.ui.fragments.order

import com.horizon.professionworker.base.Action
 import com.horizon.professionworker.data.response.NotificationResponse

sealed class NotifactionAction() : Action {
      data class ShowLoading(val show: Boolean) : NotifactionAction()
    data class ShowFailureMsg(val message: String?) : NotifactionAction()
    data class ShowNotifaction( val data: NotificationResponse) : NotifactionAction()
 }