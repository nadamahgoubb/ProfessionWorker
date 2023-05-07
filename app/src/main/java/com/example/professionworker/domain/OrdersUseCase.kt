package com.example.professionworker.domain


import com.example.professionworker.base.BaseUseCase
import com.example.professionworker.base.DevResponse
import com.example.professionworker.base.ErrorResponse
import com.example.professionworker.base.NetworkResponse
import com.example.professionworker.data.params.GetOrderParam
import com.example.professionworker.data.params.OrderActionsParams
import com.example.professionworker.data.params.OrderDetailsParam
import com.example.professionworker.data.repo.Repository
import com.example.professionworker.util.Constants


import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



@ViewModelScoped
class OrdersUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<DevResponse<Any>, Any>() {

    override fun executeRemote(params: Any?): Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>> {
        return if (params  is GetOrderParam) {
            flow {
                params?.let { repository.getOrders(params ) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }else if (params  is OrderDetailsParam) {
            flow {
                params?.let { repository.getOrderDetails(params ) }?.let { emit(it) }
            } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }


        else if  (params  is OrderActionsParams) {
            if  (params.action_type  .equals(Constants.CANCEL)){
                flow {
                    params?.let { repository.cancelOrder(params ) }?.let { emit(it) }
                } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

            }  else if  (params.action_type  .equals(Constants.ACCEPT)){
                flow {
                    params?.let { repository.acceptOrder(params ) }?.let { emit(it) }
                } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

            }  else {
                flow {
                    params?.let { repository.compeletOrder(params ) }?.let { emit(it) }
                } as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

            }


        }

        else {
            flow {
                emit( repository.getNewOrders())
            }as Flow<NetworkResponse<DevResponse<Any>, ErrorResponse>>

        }
    }

}