package com.horizon.professionworker .ui.fragment.contactFragments

import com.horizon.professionworker.data.response.GoalResponse
import com.horizon.professionworker.base.Action

sealed class SupportAction() : Action {

    data class ShowLoading(val show: Boolean) : SupportAction()
    data class ShowFailureMsg(val message: String?) : SupportAction()
    data class ShowContactSucces(val message: String?) : SupportAction()
    data class ShowComplainSucces(val message: String?) : SupportAction()
    data class ShowGoals(val data: GoalResponse?) : SupportAction()
    data class ShowTerms(val data: GoalResponse?) : SupportAction()

}
