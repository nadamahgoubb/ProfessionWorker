package com.dot_jo.professionworker.data.dataSource.remote


 import com.dot_jo.professionworker.base.BasePagingResponse
import com.dot_jo.professionworker.base.DevResponse
import com.dot_jo.professionworker.base.ErrorResponse
import com.dot_jo.professionworker.base.NetworkResponse
import com.dot_jo.professionworker.data.response.BanksResponse
import com.dot_jo.professionworker.data.response.CitesResponse
import com.dot_jo.professionworker.data.response.CountriesResponse
import com.dot_jo.professionworker.data.response.ConfrmPhoneResponse
import com.dot_jo.professionworker.data.response.GoalResponse
import com.dot_jo.professionworker.data.response.NationalitiesResponse
 import com.dot_jo.professionworker.data.response.NotificationResponse
 import com.dot_jo.professionworker.data.response.OrderdResponse
import com.dot_jo.professionworker.data.response.OrdersItem
import com.dot_jo.professionworker.data.response.ProfileResponse
import com.dot_jo.professionworker.data.response.ServicesItemsResponse
import com.dot_jo.professionworker.data.response.SubServiceItemsResponse
import com.dot_jo.professionworker.data.response.ReviewsResponse
import com.dot_jo.professionworker.data.response.UserDataResponse
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

    @Multipart
    @JvmSuppressWildcards
    @POST("provider/register")
    suspend fun register(
        @PartMap updateMap: Map<String, RequestBody>,
        @Part image: MultipartBody.Part?,
        @Part image_personal_id: MultipartBody.Part?,
        @Part("sub_service_id[]") subservice_ids: ArrayList<Int>,

        ): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse>

    @POST("cities_by_country_id")
    @FormUrlEncoded
    suspend fun getCities(
        @Field("country_id") countryId: String? = null,
    ): NetworkResponse<DevResponse<CitesResponse>, ErrorResponse>

    @POST("provider/forgot-password")
    @FormUrlEncoded
    suspend fun forgetPassword(
        @Field("phone") phone: String,
        @Field("country_code") country_code: String,
        @Field("password") password: String,
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>


    @FormUrlEncoded
    @POST("provider/confirm-phone")
    suspend fun confirmPhone(
        @Field("phone") phone: String? = null,
        @Field("country_code") country_code: String? = null
    ): NetworkResponse<DevResponse<ConfrmPhoneResponse>, ErrorResponse>
    @GET("countries")
    suspend fun getCountries(): NetworkResponse<DevResponse<CountriesResponse>, ErrorResponse>

    @GET("user/services")
    suspend fun getServices(
        @Query("page") page: Int? = null,
    ): NetworkResponse<BasePagingResponse<ServicesItemsResponse>, ErrorResponse>


    @FormUrlEncoded
    @POST("user/services/sub_service")
    suspend fun getSubServiceItemsResponse(
        @Query("page") page: Int? = null,
        @Field("service_id") service_id: String? = null,
        ): NetworkResponse<BasePagingResponse<SubServiceItemsResponse>, ErrorResponse>


  @GET("nationalities")
    suspend fun getNationalities(
    ): NetworkResponse<DevResponse<NationalitiesResponse>, ErrorResponse>
    @GET("banks")
    suspend fun getBanks(
    ): NetworkResponse<DevResponse<BanksResponse>, ErrorResponse>
    @GET("provider/goal")
    suspend fun getOurGoal(
    ): NetworkResponse<DevResponse<GoalResponse>, ErrorResponse>
  @GET("provider/terms_provider")
    suspend fun getTermsProvider(
    ): NetworkResponse<DevResponse<GoalResponse>, ErrorResponse>

    @POST("provider/contact_us")
    @FormUrlEncoded
    suspend fun contact_us(

        @Field("app_type") app_type: Int? = null,
        @Field("country_code") country_code: String? = null,
        @Field("phone") phone: String? = null,
        @Field("content") content: String? = null,
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>

    @FormUrlEncoded
    @POST("provider/complaint")
    suspend fun complain(
        @Field("provider_id") provider_id: String? = null,
        @Field("title") title: String? = null,
        @Field("content") content: String? = null,
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>

     @GET("provider/orders/reviews")
    suspend fun getReviews(
    ): NetworkResponse<DevResponse<ReviewsResponse>, ErrorResponse>

    @Multipart
    @JvmSuppressWildcards
    @POST("provider/profile/update")
    suspend fun updateProfile(
        @PartMap updateMap: Map<String, RequestBody>,
        @Part image: MultipartBody.Part?,
         @Part("sub_service_id[]") subservice_ids: ArrayList<Int>,

        ): NetworkResponse<DevResponse<UserDataResponse>, ErrorResponse>

    @POST("provider/profile/delete")
    suspend fun deleteProfile(
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>
@FormUrlEncoded
    @POST("provider/profile/change_password")
    suspend fun changePassword(
        @Field("old_password") old_password: String,
        @Field("new_password") new_password: String,

        ): NetworkResponse<DevResponse<Any>, ErrorResponse>


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
        @Field("balance") balance: String
    ): NetworkResponse<DevResponse<ProfileResponse>, ErrorResponse>

 @GET("provider/profile/notifications")
    suspend fun getNotifaction(
 ): NetworkResponse<DevResponse<NotificationResponse>, ErrorResponse>



    @FormUrlEncoded
    @POST("provider/orders")
    suspend fun getOrders(
        @Field("order_status") order_status: String? = null,
    ): NetworkResponse<DevResponse<OrderdResponse>, ErrorResponse>

     @POST("provider/orders/new")
    suspend fun getNewOrders(
     ): NetworkResponse<DevResponse<OrderdResponse>, ErrorResponse>

    @FormUrlEncoded
    @POST("provider/orders/order_by_id")
    suspend fun getOrderDetails(
         @Field("order_id") order_id: String
     ): NetworkResponse<DevResponse<OrdersItem>, ErrorResponse>


    @FormUrlEncoded
    @POST("provider/orders/acceptance_order")
    suspend fun acceptOrder(
        @Field("order_id") order_id: String,
        @Field("order_status") order_status: String
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>

    @FormUrlEncoded
    @POST("provider/orders/cancel_order")
    suspend fun cancelOrder(
        @Field("order_id") order_id: String,
        @Field("order_status") order_status: String

    ): NetworkResponse<DevResponse<Any>, ErrorResponse>

    @FormUrlEncoded
    @POST("provider/orders/completed_order")
    suspend fun compeletOrder(
        @Field("order_id") order_id: String,
        @Field("order_status") order_status: String
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>
    @FormUrlEncoded
    @POST("provider/update_fcm_token")
    suspend fun updateFcm(
        @Field("fcm_token") fcm_token: String? = null,
        @Field("mobile_id") mobile_id: Int? = 0,
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>

    @FormUrlEncoded
    @POST("provider/profile/update_balance")
    suspend fun updateBalance(
        @Field("balance") balance: String,
        @Field("trans_ref") trans_ref: String,
        @Field("pay_token") pay_token: String,
    ): NetworkResponse<DevResponse<Any>, ErrorResponse>
    @FormUrlEncoded
    @POST("provider/profile/withdraw_money")
    suspend fun withdrawBalance(
        @Field("value") value: String,

    ): NetworkResponse<DevResponse<Any>, ErrorResponse>

}
