package com.dot_jo.professionworker.base


import com.dot_jo.professionworker.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

abstract class BaseUseCase<RequestType : BaseResponse, params : Any> :
    BaseCommonUseCase<RequestType, params>() {
    fun invoke(
        scope: CoroutineScope,
        params: params? = null,
        onResult: (Resource<RequestType>) -> Unit = {}
    ) {
        scope.launch(handler(onResult) + Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            runFlow(executeRemote(params), onResult).collect {
             when (it) {
                    is NetworkResponse.Success -> {
                        if (it.code == 200
                        ) {
                                onResult.invoke(Resource.success(it.body))
                            }

                    }

                    is NetworkResponse.NetworkError -> {
                       if(it.error.message?.contains("professions.")== true) {
                             showFailureMessage(
                               onResult,
                              "Connection Error"
                           )
                       }
                        else showFailureMessage(
                               onResult,
                               it.error.message
                           )

                    }
                    is NetworkResponse.UnknownError -> showFailureMessage(
                        onResult,
                        it.error.toString()
                    )
                    is NetworkResponse.ServerError -> {

                     if   (it.code == 401)  showFailureMessage(onResult, "401"+it.body?.Error)

                     else    showFailureMessage(onResult, it.body?.Error)

                    }
                 else -> {
                  //    showFailureMessage(onResult, it.)


                 }

                }
                    onResult.invoke(Resource.loading(false))
                }
            }
        }
    }
