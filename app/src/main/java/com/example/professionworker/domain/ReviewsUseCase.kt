package com.example.professionworker.domain


import com.example.professionworker.base.BaseUseCase
import com.example.professionworker.base.DevResponse
import com.example.professionworker.base.ErrorResponse
import com.example.professionworker.base.NetworkResponse
 import com.example.professionworker.data.repo.Repository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



@ViewModelScoped
class ReviewsUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<DevResponse<Any>, Any>() {

    override fun executeRemote(params: Any?): Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>> {
        return   flow {
                emit(repository.getReviews())
            }as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }


}