package com.horizon.professionworker.data.repo



import com.horizon.professionworker.base.BasePagingResponse
import com.horizon.professionworker.base.DevResponse
import com.horizon.professionworker.base.ErrorResponse
import com.horizon.professionworker.base.NetworkResponse
import com.horizon.professionworker.data.dataSource.remote.ApiInterface
import com.horizon.professionworker.data.params.*
import com.horizon.professionworker.data.response.BanksResponse
import com.horizon.professionworker.data.response.CitesResponse
import com.horizon.professionworker.data.response.CountriesResponse
import com.horizon.professionworker.data.response.GoalResponse
import com.horizon.professionworker.data.response.NationalitiesResponse
import com.horizon.professionworker.data.response.OrderdResponse
import com.horizon.professionworker.data.response.OrdersItem
import com.horizon.professionworker.data.response.ProfileResponse
import com.horizon.professionworker.data.response.ServicesItemsResponse
import com.horizon.professionworker.data.response.SubServiceItemsResponse
import com.horizon.professionworker.data.response.ReviewsResponse
import com.horizon.professionworker.data.response.UserDataResponse
import  com.horizon.professionworker.util.FileManager.toMultiPart
import com.horizon.professionworker.util.fcm.FcmParam

import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiInterface) {

    suspend fun login(param: LoginParms): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse> {
        return api.login(param.countryCode,param.phone, param.password)
    }

    suspend fun register(
     param:RegisterParams
    ): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse> {

            return api.register( param.toMap(),
                param.photo?.toMultiPart("photo")  ,
                param.personal_id_photo?. toMultiPart("personal_id_photo")  ,
param.sub_service_id
        )


    }

   suspend fun getCities(params: CityParams): NetworkResponse<DevResponse<CitesResponse>, ErrorResponse> {
        return api.getCities(params.countryId)
    }
    suspend fun getProfile( ): NetworkResponse<DevResponse<ProfileResponse>, ErrorResponse> {
        return api.getProfile()
    }
    suspend fun forgetPassword(params: ForgetPasswordParms): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.forgetPassword(params.phone, params.country_code, params.password)
    }    suspend fun confirmPhone(params: ConfirmPhoneParams) =
      api.confirmPhone(params.phone, params.country_code )


    suspend fun getCountries( ): NetworkResponse<DevResponse<CountriesResponse>, ErrorResponse> {
        return api.getCountries( )
    }

       suspend fun getServices(page: Int): NetworkResponse<BasePagingResponse<ServicesItemsResponse>, ErrorResponse> {
           return api.getServices(page)
       }


    suspend fun getSubServiceItemsResponse(page: Int , serviceId:String): NetworkResponse<BasePagingResponse<SubServiceItemsResponse>, ErrorResponse> {
        return api.getSubServiceItemsResponse(page  , serviceId)
    }
    suspend fun getReviews( ): NetworkResponse<DevResponse<ReviewsResponse>, ErrorResponse> {
        return api.getReviews(  )
    }
    suspend fun deleteProfile( ): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.deleteProfile(  )
    }  suspend fun changePassword(param: ChangePasswordParam ): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.changePassword( param.old_pass, param.new_pass )
    }
    suspend fun contact_us(params:ContactUsParms ): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.contact_us( 1,params.countryCode,params.phone, params.content )
    }
    suspend fun getOurGoal( ): NetworkResponse<DevResponse<GoalResponse>, ErrorResponse> {
        return api.getOurGoal(  )
    }
    suspend fun getTermsProvider( ): NetworkResponse<DevResponse<GoalResponse>, ErrorResponse> {
        return api.getTermsProvider(  )
    }
    suspend fun complain(params: ComplainParms ): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.complain(params.providerId, params.title, params.content  )
    }

    suspend fun getNationalities( ): NetworkResponse<DevResponse<NationalitiesResponse>, ErrorResponse> {
        return api.getNationalities(   )
    } suspend fun getBanks(  ): NetworkResponse<DevResponse<BanksResponse>, ErrorResponse> {
        return api.getBanks(  )
    }
    suspend fun updateProfile(param:EditProfileParams ): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse> {
        return api.updateProfile(  param.toMap(),   param.photo?.toMultiPart("photo")  ,
             param.sub_service_id )
    }
    suspend fun getOrders(params: GetOrderParam): NetworkResponse<DevResponse<OrderdResponse>, ErrorResponse> {
        return api.getOrders( params.order_status)
    }
    suspend fun getNewOrders( ): NetworkResponse<DevResponse<OrderdResponse>, ErrorResponse> {
        return api.getNewOrders(  )
    }
    suspend fun getNotifications()
            =api.getNotifaction()
    suspend fun getOrderDetails(params:OrderDetailsParam ): NetworkResponse<DevResponse<OrdersItem>, ErrorResponse> {
        return api.getOrderDetails( params.order_id )
    }
 suspend fun acceptOrder(param:OrderActionsParams ): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.acceptOrder( param.order_id,param.status_id  )
    }
 suspend fun cancelOrder( param:OrderActionsParams): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.cancelOrder( param.order_id,"cancel" )
    }
 suspend fun compeletOrder( param:OrderActionsParams): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.compeletOrder(param.order_id,param.status_id  )
    }
    suspend fun updateFcm(params: FcmParam): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.updateFcm(params.token)
    }

    suspend fun updateBalance(params: UpdateBalanceParam): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.updateBalance(params.amount, params.trans_ref, params.pay_token)
    }
   suspend fun withdrawBalance(params: WithdrawBalanceParam)= api.withdrawBalance(params.value )



}

