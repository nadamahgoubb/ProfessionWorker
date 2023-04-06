package com.example.professionworker.data.repo



import com.example.profession.data.dataSource.response.CitesResponse
import com.example.profession.data.dataSource.response.CountriesResponse
import com.example.professionworker.base.DevResponse
import com.example.professionworker.base.ErrorResponse
import com.example.professionworker.base.NetworkResponse
import com.example.professionworker.data.dataSource.remote.ApiInterface
import com.example.professionworker.data.params.*
import com.example.professionworker.data.response.UserDataResponse
import okhttp3.MultipartBody
import  com.example.professionworker.util.FileManager.toMultiPart

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
    }suspend fun forgetPassword(params: ForgetPasswordParms): NetworkResponse<DevResponse<Any>, ErrorResponse> {
        return api.forgetPassword(params.phone, params.country_code, params.password)
    }

    suspend fun getCountries( ): NetworkResponse<DevResponse<CountriesResponse>, ErrorResponse> {
        return api.getCountries( )
    }
     /*
       suspend fun getServices(page: Int): NetworkResponse<BasePagingResponse<ServicesItemsResponse>, ErrorResponse> {
           return api.getServices(page)
       }

       suspend fun getSlider( ): NetworkResponse<DevResponse<SliderResponse>, ErrorResponse> {
           return api.getSlider()
       }
   */
}

