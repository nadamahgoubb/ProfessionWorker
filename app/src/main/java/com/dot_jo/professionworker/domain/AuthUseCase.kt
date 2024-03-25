package com.dot_jo.professionworker.domain

import com.dot_jo.professionworker.base.BaseUseCase
import com.dot_jo.professionworker.base.DevResponse
import com.dot_jo.professionworker.base.ErrorResponse
import com.dot_jo.professionworker.base.NetworkResponse
import com.dot_jo.professionworker.data.params.CityParams
import com.dot_jo.professionworker.data.params.ConfirmPhoneParams
import com.dot_jo.professionworker.data.params.ForgetPasswordParms
import com.dot_jo.professionworker.data.params.LoginParms
import com.dot_jo.professionworker.data.params.RegisterParams
import com.dot_jo.professionworker.data.repo.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



@ViewModelScoped
class AuthUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<DevResponse<Any>, Any>() {

    override fun executeRemote(params: Any?): Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>> {
        return if (params is LoginParms) {
            flow {
                params?.let { repository.login(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }else if (params is RegisterParams) {
            flow {
                params?.let { repository.register(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        } else if (params is CityParams) {
            flow {
                params?.let { repository.getCities(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        } else  if (params is ForgetPasswordParms) {
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
                emit( repository.getCountries())
            }as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
    }

}
