package com.horizon.professionworker.util.fcm

import androidx.lifecycle.viewModelScope
import com.horizon.professionworker.base.BaseUseCase
import com.horizon.professionworker.base.DevResponse
import com.horizon.professionworker.base.ErrorResponse
import com.horizon.professionworker.base.NetworkResponse
import com.horizon.professionworker.data.repo.PrefsHelper
import com.horizon.professionworker.data.repo.Repository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Interceptor
import java.net.Authenticator.RequestorType
import javax.inject.Inject
import javax.inject.Singleton



private const val TAG = "FcmUseCase"

@Singleton
class FcmUseCase @Inject constructor(
    val repo: Repository
)  :  BaseUseCase<DevResponse<Any>, Any>() {

    fun generateFcmToken(callBack: (String?) -> Unit = {}) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            PrefsHelper.saveFcmtoken(token)
            sendFcmTokenToServer(FcmParam(token))
        })
    }

    fun generateFcmTokenIfNotExist() {

        if (PrefsHelper.getFcmToken().isNullOrBlank()) {
            generateFcmToken()
        }
    }
    fun sendFcmTokenToServer(params: FcmParam) {
    invoke(CoroutineScope(Dispatchers.IO), params = params)
    }


    override fun executeRemote(params: Any?): Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>> {
        return flow {
            emit(repo.updateFcm(params!! as FcmParam))
        } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>
    }

}