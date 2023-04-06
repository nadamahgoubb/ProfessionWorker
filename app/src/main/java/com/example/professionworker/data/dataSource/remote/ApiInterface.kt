package com.example.professionworker.data.dataSource.remote


import com.example.profession.data.dataSource.response.CitesResponse
import com.example.profession.data.dataSource.response.CountriesResponse
import com.example.profession.data.dataSource.response.ProfileResponse
import com.example.professionworker.base.DevResponse
import com.example.professionworker.base.ErrorResponse
import com.example.professionworker.base.NetworkResponse
import com.example.professionworker.data.response.UserDataResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import javax.inject.Singleton

@Singleton

interface ApiInterface {

    @FormUrlEncoded
    @POST("provider/login")
    suspend fun login(
        @Field("phone") phone: String, @Field("password") password: String
    ): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse>

    @FormUrlEncoded
    @POST("provider/register")
    suspend fun register(
        @PartMap updateMap: Map<String, RequestBody>,
        @Part image: MultipartBody.Part?,
        @Part image_personal_id: MultipartBody.Part?,
        @Part ("subservice_ids[]") subservice_ids :ArrayList<String>,

        ): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse>
    @FormUrlEncoded
    @POST("provider/profile/update")
    suspend fun updateProfile(
        @PartMap updateMap: Map<String, RequestBody>,
        @Part image: MultipartBody.Part?,
        @Part image_personal_id: MultipartBody.Part?,
        @Part ("subservice_ids[]") subservice_ids :ArrayList<String>,

        ): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse>



    @FormUrlEncoded
    @POST("provider/logout")
    suspend fun logout(

        ): NetworkResponse<DevResponse<Any>, ErrorResponse>
  @FormUrlEncoded
    @POST("provider/forgot-password")
    suspend fun forgotPassword(
      @Field("phone") phone: String,
      @Field("country_code") country_code: String,

      @Field("password") password: String

        ): NetworkResponse<DevResponse<Any>, ErrorResponse>
 @GET("provider/profile")
    suspend fun getProfile(
      ): NetworkResponse<DevResponse<ProfileResponse>, ErrorResponse>
 @GET("provider/profile/update_balance")
    suspend fun updateBalance(
     @Field("balance") balance: String  ): NetworkResponse<DevResponse<ProfileResponse>, ErrorResponse>



    @POST("cities_by_country_id")
    @FormUrlEncoded
    suspend fun getCities(
        @Field("country_id") countryId: String? = null,
    ): NetworkResponse<DevResponse<CitesResponse>, ErrorResponse>
    @POST("user/forgot-password")
    @FormUrlEncoded
    suspend fun forgetPassword(
        @Field("phone") phone: String ,
        @Field("country_code") country_code: String ,
        @Field("password") password: String  ,
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>


    @GET("countries")
    suspend fun getCountries(): NetworkResponse<DevResponse<CountriesResponse>, ErrorResponse>
/*
    @GET("user/services")
    suspend fun getServices(
        @Query("page") page: Int? = null,

        ): NetworkResponse<BasePagingResponse<ServicesItemsResponse>, ErrorResponse>

    @GET("user/slider")
    suspend fun getSlider(

    ): NetworkResponse<DevResponse<SliderResponse>, ErrorResponse>
*/

}