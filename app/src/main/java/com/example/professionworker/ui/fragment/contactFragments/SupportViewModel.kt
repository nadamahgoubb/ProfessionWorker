package com.example.professionworker.ui.fragment.contactFragments

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.profession.data.dataSource.response.*
import com.example.professionworker.R
import com.example.professionworker.base.BaseViewModel
import com.example.professionworker.data.params.*
import com.example.professionworker.data.repo.PrefsHelper
import com.example.professionworker.domain.*
 import com.example.professionworker.util.NetworkConnectivity
import com.example.professionworker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportViewModel
@Inject constructor(
    app: Application,
    val useCase: SupportUseCase,
) : BaseViewModel<SupportAction>(app) {

    fun validateComplain(title:String,
        content: String
    ): Boolean {
        return if (content.isNullOrBlank()) {
            produce(SupportAction.ShowFailureMsg(getString(R.string.msg_empty_content)))
            false
        } else if (title.isNullOrBlank()) {
            produce(SupportAction.ShowFailureMsg(getString(R.string.msg_empty_title)))
            false
        }  else {

            PrefsHelper.getUserData()?.let {
                it.id?.toString()
                    ?.let { it1 -> ComplainParms(it1, title, content) }
                    ?.let { it2 -> complain(it2) }
            }
            true
        }
    }
    fun contact(content: String) {
    if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


        produce(SupportAction.ShowLoading(true))
        useCase.invoke(
            viewModelScope,SupportParms(1,content)//1 for provider app
        ) { res ->
            when (res) {
                is Resource.Failure -> produce(SupportAction.ShowFailureMsg(res.message.toString()))
                is Resource.Progress -> produce(SupportAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(
                        SupportAction.ShowContactSucces(
                            res.data.message as String
                        )
                    )
                }
            }
        }
    } else {
        produce(SupportAction.ShowFailureMsg(getString(R.string.no_internet)))
    }
}
    fun complain(parms: ComplainParms) {
    if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


        produce(SupportAction.ShowLoading(true))
        useCase.invoke(
            viewModelScope,parms
        ) { res ->
            when (res) {
                is Resource.Failure -> produce(SupportAction.ShowFailureMsg(res.message.toString()))
                is Resource.Progress -> produce(SupportAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(
                        SupportAction.ShowComplainSucces(
                            res.data.message as String
                        )
                    )
                }
            }
        }
    } else {
        produce(SupportAction.ShowFailureMsg(getString(R.string.no_internet)))
    }
}
    fun getGoals( ) {
    if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


        produce(SupportAction.ShowLoading(true))
        useCase.invoke(
            viewModelScope
        ) { res ->
            when (res) {
                is Resource.Failure -> produce(SupportAction.ShowFailureMsg(res.message.toString()))
                is Resource.Progress -> produce(SupportAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(
                        SupportAction.ShowGoals(
                            res.data.data as GoalResponse
                        )
                    )
                }
            }
        }
    } else {
        produce(SupportAction.ShowFailureMsg(getString(R.string.no_internet)))
    }
}  fun getTerms( ) {
    if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


        produce(SupportAction.ShowLoading(true))
        useCase.invoke(
            viewModelScope, SupportUseCase.terms
        ) { res ->
            when (res) {
                is Resource.Failure -> produce(SupportAction.ShowFailureMsg(res.message.toString()))
                is Resource.Progress -> produce(SupportAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(
                        SupportAction.ShowTerms(
                            res.data.data as GoalResponse
                        )
                    )
                }
            }
        }
    } else {
        produce(SupportAction.ShowFailureMsg(getString(R.string.no_internet)))
    }
}
}



