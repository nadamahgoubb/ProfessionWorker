package com.dot_jo.professionworker.data.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@Parcelize
data class ChangePasswordParam(

    val old_pass: String,
    val new_pass: String,

    ) : Parcelable


@Parcelize
data class EditProfileParams(

    val name: String,
    val nationalcard_id: String?,
    val commercial_registration_no: String?,
    val countryId: String? = null,
    val cityId: String? = null,
    val nationality_id: String? = null,
    val address: String? = null,
    val lat: String,
    val lon: String,
    val email: String,
    val phone: String,
    val country_code: String,
    var service_id: String,
    var previous_experience: String,
    var years_experience: String,
    var hour_price: String,
    var photo: File?,
    var account_number: String,
    var account_name: String,
    var bank_id: String,
    var iban_number: String,
    var mobile_id: String = "0",
    @SerializedName("sub_service_id")
    var sub_service_id: ArrayList<Int> = arrayListOf()
) : Parcelable


fun EditProfileParams.toMap(): Map<String, RequestBody> {

    val itemMap = hashMapOf<String, RequestBody>()

    itemMap["name"] = name.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
   nationalcard_id?.let {
       itemMap["national_id"] =it.toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }

        commercial_registration_no?.let {
            itemMap["commercial_registration_no"] =it.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        }
    itemMap["country_id"] = countryId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["city_id"] = cityId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["nationality_id"] = nationality_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["address"] = address.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["lat"] = lat.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["lon"] = lon.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())

    itemMap["country_code"] = country_code.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["email"] = email.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["phone"] = phone.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["service_id"] = service_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["previous_experience"] =
        previous_experience.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["years_experience"] =
        years_experience.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["hour_price"] =
        hour_price.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["account_number"] =
        account_number.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["account_name"] =
        account_name.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["bank_id"] = bank_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["iban_number"] =
        iban_number.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["mobile_id"] =
        mobile_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())



    return itemMap
}
