package com.dot_jo.professionworker.ui.fragments.order

import com.dot_jo.professionworker.base.Action
 import com.dot_jo.professionworker.data.response.NotificationResponse

sealed class NotifactionAction() : Action {
      data class ShowLoading(val show: Boolean) : NotifactionAction()
    data class ShowFailureMsg(val message: String?) : NotifactionAction()
    data class ShowNotifaction( val data: NotificationResponse) : NotifactionAction()
 }
