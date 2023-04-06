package com.example.professionworker.ui.fragment.login

 import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.professionworker.R
import com.example.professionworker.base.BaseViewModel
import com.example.professionworker.data.params.ForgetPasswordParms
import com.example.professionworker.data.params.LoginParms
import com.example.professionworker.data.params.RegisterParams
import com.example.professionworker.data.repo.PrefsHelper
import com.example.professionworker.data.response.UserDataResponse
 import com.example.professionworker.util.NetworkConnectivity
import com.example.professionworker.util.Resource
import com.example.professionworker.domain.AuthUseCase
import com.example.professionworker.util.Extension
import com.example.professionworker.util.ext.isNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class AuthViewModel
@Inject constructor(app: Application, val authUserCase: AuthUseCase) : BaseViewModel<AuthAction>(app) {
    var name: String? = null
    var email: String? = null
    var country_code: String? = null
    var phone: String? = null
    var password: String? = null
    var lat: Double? = null
    var lon: Double? = null

    var type: String? = null
    var national_id: String? = null
    var countryId: String? = null
    var cityId: String? = null
    var nationality_id: String? = null
    var address: String? = null

    var countryCode: String? = null
    var service_id: String? = null
    var previous_experience: String? = null
    var years_experience: String? = null
    var hour_price: String? = null
    var paaword: String? = null
    var photo: File? = null
    var personal_id_photo: File? = null
    var account_number: String? = null
    var account_name: String? = null
    var bank_id: String? = null
    var iban_number: String? = null
    var mobile_id: String? = null
    var sub_service_id: ArrayList<String>? = null



    fun validateRegisteration(
        name: String,
        national_id: String,
        countryId: String,
        cityId: String,
        nationality_id: String,
        lat: Double?,
        lon: Double?,
        address: String?,
        sub_service_id: ArrayList<String> = arrayListOf(),
        countryCode: String?,
        email: String,
        phone: String,
         service_id: String?,
        previous_experience: String?,
        years_experience: String?,
        hour_price: String?,
        photo: File?,
        personal_id_photo: File?, pass: String,

        ): Boolean {
        return if (name.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_user_name)))
            false
        } else if (national_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_national_id)))
            false
        }
        else if (countryId.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_country_id)))
            false
        } else if (cityId.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_cityid)))
            false
        } else if (nationality_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_nationality_id)))
            false
        }
        else if (lat.isNull() || lon?.equals(0) == true || address.isNullOrEmpty()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.enter_your_location)))
            false
        }    else if  (sub_service_id.isNullOrEmpty()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_sub_service)))
            false
        }
        else if (phone.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_num)))
            false
        } else if (email.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_email)))
            false
        } else if (!Extension.isEmailValid(email)) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_invalide_email)))
            false
        } else if (countryCode.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_num_code)))
            false
        } else if (service_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_service_id)))
            false
        } else if (years_experience.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_years_of_experience)))
            false
        } else if (previous_experience.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.emptypervious_exper)))
            false
        } else if (personal_id_photo.isNull()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_personal_id_photo)))
            false
        } else if (photo.isNull()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_photo)))
            false
        } else if (hour_price.isNull()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_hour_price)))
            false
        } else if (pass.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_password)))
            false
        }  else {

        /*    this.name = name
            this.phone = phone
            this.email = email
            this.country_code = country_code
            this.countryId = countryId
            this.cityId = cityId
            this.password = pass
            this.lon = lon
            this.lat = lat
            register(
                RegisterParams(
                    name,
                    phone,
                    email,
                    country_code,
                    countryId,
                    cityId,
                    pass,
                    lat.toString(),
                    lon.toString(),
                    "0",
                    address
                )
            )*/
            true

        }
    }



    fun isValidParams(phone: String?, pass: String?): Boolean {

        return if (phone.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.please_enter_your_phone)))
            false
        } else if (pass.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_password)))
            false
        } else {
            login(phone, pass)
            true
        }

    }


    fun login(phone: String, pass: String) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(AuthAction.ShowLoading(true))
            authUserCase.invoke(
                viewModelScope, LoginParms(
                    phone, pass
                )
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        PrefsHelper.saveToken((res.data.data as UserDataResponse).token)
                        PrefsHelper.saveUserData(res.data.data as UserDataResponse)
                        produce(AuthAction.LoginSuccess(res.data.data as UserDataResponse))

                    }
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }


    fun register(
        params: RegisterParams
    ) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {
            produce(AuthAction.ShowLoading(true))

            viewModelScope.launch {
                var res = authUserCase.invoke(
                    viewModelScope, params
                )

                { res ->
                    when (res) {
                        is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                        is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                        is Resource.Success -> {
                            PrefsHelper.saveToken((res.data.data as UserDataResponse).token)
                            PrefsHelper.saveUserData(res.data.data as UserDataResponse)
                            produce(AuthAction.RegisterationSuccess(res.data.data as UserDataResponse))

                        }
                    }
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun forgetPassword(
        phone: String, country_code: String, password: String
    ) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(AuthAction.ShowLoading(true))
            authUserCase.invoke(
                viewModelScope, ForgetPasswordParms(phone, country_code, password)
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(AuthAction.ShowForgetPassword(res.data.message as String))

                    }
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }
    /*  fun getAllCitiesByCountryId(country_id: String) {
  
  
          if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {
  
  
              produce(AuthAction.ShowLoading(true))
              authUserCase.invoke(
                  viewModelScope, CityParams(country_id)
              ) { res ->
                  when (res) {
                      is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                      is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                      is Resource.Success -> {
                          produce(AuthAction.ShowAllCities(res.data.data as CitesResponse))
  
                      }
                  }
              }
          } else {
              produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
          }
      }
  
      fun getAllCountry() {
          if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {
  
  
              produce(AuthAction.ShowLoading(true))
              authUserCase.invoke(
                  viewModelScope
              ) { res ->
                  when (res) {
                      is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                      is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                      is Resource.Success -> {
                          produce(AuthAction.ShowAllCountry(res.data.data as CountriesResponse))
  
                      }
                  }
              }
          } else {
              produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
          }
      }
  */
}

 



