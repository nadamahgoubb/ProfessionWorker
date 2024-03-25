package com.dot_jo.professionworker.domain


import com.dot_jo.professionworker.base.BaseUseCase
import com.dot_jo.professionworker.base.DevResponse
import com.dot_jo.professionworker.base.ErrorResponse
import com.dot_jo.professionworker.base.NetworkResponse
import com.dot_jo.professionworker.data.params.*
import com.dot_jo.professionworker.data.repo.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



@ViewModelScoped
class SupportUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<DevResponse<Any>, Any>() {
companion object support{
    val terms =1
}
    override fun executeRemote(params: Any?): Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>> {
        return if (params is ComplainParms) {
            flow {
                params?.let { repository.complain(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }else if (params is ContactUsParms) {
            flow {
                params?.let { repository.contact_us(it) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }else if (params ?.equals(terms) == true) {
            flow {

                emit( repository.getTermsProvider())
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
        else {
            flow {
                emit( repository.getOurGoal())
            }as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
    }

}
