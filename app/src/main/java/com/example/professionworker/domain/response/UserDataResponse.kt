package com.example.professionworker.domain.response

import com.example.profession.data.dataSource.response.SubServiceItemsResponse
import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("account_name") val account_name: String,
    @SerializedName("account_number") val account_number: String,
    @SerializedName("address") val address: String,
    @SerializedName("bank_id") val bank_id: String,
    @SerializedName("city_id") val city_id: String,
    @SerializedName("commercial_registration_no") val commercial_registration_no: Any,
    @SerializedName("country_code") val country_code: String,
    @SerializedName("country_id") val country_id: String,
    @SerializedName("country_name") val country_name: String,
    @SerializedName("email") val email: String,
    @SerializedName("email_verified_at") val email_verified_at: String,
    @SerializedName("hour_price") val hour_price: String,
    @SerializedName("iban_number") val iban_number: String,
    @SerializedName("bank_name") val bank_name: String,
    @SerializedName("id") val id: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lon") val lon: String,
    @SerializedName("mobile_id") val mobile_id: String,
    @SerializedName("name") val name: String,
    @SerializedName("national_id") val national_id: String,
    @SerializedName("avg_rate") val total_rate: String,
    @SerializedName("count_reviews") val count_reviews: String,
    @SerializedName("service_name") val service_name: String,

    @SerializedName("balance") val balance: String,
    @SerializedName("nationality_id") val nationality_id: String,
    @SerializedName("password") val password: String,
    @SerializedName("personal_id_photo") val personal_id_photo: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("previous_experience") val previous_experience: String,
    @SerializedName("service_id") val service_id: String,
    @SerializedName("token") val token: String,
    @SerializedName("token_type") val token_type: String,
    @SerializedName("type") val type: String,
    @SerializedName("years_experience") val years_experience: String,
    @SerializedName("sub_services") val sub_services: ArrayList<SubServiceItemsResponse> = arrayListOf()


)