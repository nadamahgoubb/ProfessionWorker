package com.horizon.professionworker.domain


import com.horizon.professionworker.base.BaseUseCase
import com.horizon.professionworker.base.DevResponse
import com.horizon.professionworker.base.ErrorResponse
import com.horizon.professionworker.base.NetworkResponse
import com.horizon.professionworker.data.params.*
import com.horizon.professionworker.data.repo.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@ViewModelScoped
class ProfileUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<DevResponse<Any>, Any>() {
    companion object ProfileActions{
      val  GetProfileData=0
       val  DELETE_ACCOUNT =1
        val GET_BANKS =2
        val GET_NATIONALITIES =3

    }
    override fun executeRemote(params: Any?): Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>> {
        return if (params ?.equals(DELETE_ACCOUNT) == true) {
            flow {
               emit(repository.deleteProfile())
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>
        }
        else if (params is EditProfileParams) {
            flow {
                params?.let { repository.updateProfile(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
        else if (params?.equals( GET_BANKS) == true) {
            flow {
               emit(repository.getBanks())
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
          else if (params is WithdrawBalanceParam ) {
            flow {
               emit(repository.withdrawBalance(params))
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
        else if (params?.equals( GET_NATIONALITIES) == true) {
            flow {
                emit(repository.getNationalities() )
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        } else if (params is ChangePasswordParam) {
            flow {
                emit(repository.changePassword(params) )
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
        else {
            flow {
                emit( repository.getProfile())
            }as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
    }

}
