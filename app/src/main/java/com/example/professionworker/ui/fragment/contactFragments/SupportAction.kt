package com.example.professionworker .ui.fragment.contactFragments

import com.example.profession.data.dataSource.response.GoalResponse
import com.example.professionworker.base.Action

sealed class SupportAction() : Action {

    data class ShowLoading(val show: Boolean) : SupportAction()
    data class ShowFailureMsg(val message: String?) : SupportAction()
    data class ShowContactSucces(val message: String?) : SupportAction()
    data class ShowComplainSucces(val message: String?) : SupportAction()
    data class ShowGoals(val data: GoalResponse?) : SupportAction()
    data class ShowTerms(val data: GoalResponse?) : SupportAction()

}
