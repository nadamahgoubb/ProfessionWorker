package com.example.professionworker.data.repo



import com.example.profession.data.dataSource.response.*
import com.example.professionworker.base.BasePagingResponse
import com.example.professionworker.base.DevResponse
import com.example.professionworker.base.ErrorResponse
import com.example.professionworker.base.NetworkResponse
import com.example.professionworker.data.dataSource.remote.ApiInterface
import com.example.professionworker.data.params.*
import com.example.professionworker.domain.response.OrderdResponse
import com.example.professionworker.domain.response.OrdersItem
import com.example.professionworker.domain.response.ReviewsResponse
import com.example.professionworker.domain.response.UserDataResponse
import okhttp3.MultipartBody
import  com.example.professionworker.util.FileManager.toMultiPart
import okhttp3.RequestBody
import retrofit2.http.*

import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiInterface) {

    suspend fun login(param: LoginParms): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse> {
        return api.login(param.phone, param.password)
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
    }

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
    suspend fun contact_us(params:SupportParms ): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.contact_us( params.app_type, params.content )
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
    suspend fun getOrderDetails(params:OrderDetailsParam ): NetworkResponse<DevResponse<OrdersItem>, ErrorResponse> {
        return api.getOrderDetails( params.order_id )
    }
 suspend fun acceptOrder(param:OrderActionsParams ): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.acceptOrder( param.order_id,param.status_id  )
    }
 suspend fun cancelOrder( param:OrderActionsParams): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.cancelOrder( param.order_id,param.status_id  )
    }
 suspend fun compeletOrder( param:OrderActionsParams): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.compeletOrder(param.order_id,param.status_id  )
    }



}

