package com.horizon.professionworker.domain


import com.horizon.professionworker.base.BaseUseCase
import com.horizon.professionworker.base.DevResponse
import com.horizon.professionworker.base.ErrorResponse
import com.horizon.professionworker.base.NetworkResponse
 import com.horizon.professionworker.data.params.ConfirmPhoneParams
import com.horizon.professionworker.data.params.ForgetPasswordParms
import com.horizon.professionworker.data.repo.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



@ViewModelScoped
class ForgetPassUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<DevResponse<Any>, Any>() {

    override fun executeRemote(params: Any?): Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>> {
   return if (params is ForgetPasswordParms) {
            flow {
                params?.let { repository.forgetPassword(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
 else if (params is ConfirmPhoneParams) {
            flow {
                params?.let { repository.confirmPhone(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }

        else {
            flow {
                emit(null )
            }as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
     }

}
