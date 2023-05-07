package com.example.professionworker.data.params

import com.example.professionworker.base.PagingParams
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


data class LoginParms(


    var phone: String = "",
    var password: String = ""

)

data class ForgetPasswordParms(


    var phone: String = "",
    var country_code: String = "",
    var password: String = ""

)

data class RegisterParams(
    var name: String,
    var type: String,
    var national_id: String,
    var countryId: String? = null,
    var cityId: String? = null,
    var nationality_id: String? = null,
    var address: String,
    var lat: String,
    var lon: String,
    var phone: String,
    var email: String,
    var countryCode: String,
    var service_id: String,
    var previous_experience: String,
    var years_experience: String,
    var hour_price: String,
    var paaword: String,
    var photo: File,
    var personal_id_photo: File,
    var account_number: String,
    var account_name: String,
    var bank_id: String,
    var iban_number: String,
    var mobile_id: String = "0",
    var sub_service_id: ArrayList<Int>
)


fun RegisterParams.toMap(): Map<String, RequestBody> {

    val itemMap = hashMapOf<String, RequestBody>()

    itemMap["name"] = name.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["type"] = type.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["national_id"] =
        national_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["country_id"] =
        countryId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["city_id"] = cityId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["nationality_id"] =
        nationality_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["address"] = address.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["lat"] = lat.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["lon"] = lon.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["phone"] = phone.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["email"] = email.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["country_code"] =
        countryCode.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["service_id"] =
        service_id.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["previous_experience"] =
        previous_experience.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["years_experience"] =
        years_experience.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["hour_price"] =
        hour_price.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
    itemMap["password"] = paaword.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
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

data class SubServicesParams(var service_id: String) : PagingParams() {

}
