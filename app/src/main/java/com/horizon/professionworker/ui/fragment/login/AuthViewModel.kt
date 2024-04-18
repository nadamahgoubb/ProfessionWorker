package com.horizon.professionworker.ui.fragment.login

  import android.app.Application
import androidx.lifecycle.viewModelScope
 import com.horizon.professionworker.R
import com.horizon.professionworker.base.BaseViewModel
import com.horizon.professionworker.base.PagingParams
import com.horizon.professionworker.data.params.*
import com.horizon.professionworker.data.repo.PrefsHelper
import com.horizon.professionworker.data.response.BanksResponse
import com.horizon.professionworker.data.response.CitesResponse
import com.horizon.professionworker.data.response.ConfrmPhoneResponse
import com.horizon.professionworker.data.response.CountriesResponse
import com.horizon.professionworker.data.response.NationalitiesResponse
import com.horizon.professionworker.data.response.ProfileResponse
import com.horizon.professionworker.domain.*
import com.horizon.professionworker.data.response.UserDataResponse
import com.horizon.professionworker.util.NetworkConnectivity
import com.horizon.professionworker.util.Resource
 import com.horizon.professionworker.data.response.ReviewsResponse
  import com.horizon.professionworker.domain.ProfileUseCase.ProfileActions.DELETE_ACCOUNT
  import com.horizon.professionworker.util.Extension
import com.horizon.professionworker.util.ext.isNull
  import com.google.gson.annotations.SerializedName
  import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class AuthViewModel
@Inject constructor(
    app: Application,
    val authUserCase: AuthUseCase,
    val useCaseProfile: ProfileUseCase,
    var usecaseService: ServicesPagingUseCase,
    var usecasereviews: ReviewsUseCase,
    var usecaseSubService: SubServicesPagingUseCase
) : BaseViewModel<AuthAction>(app) {
    var name: String = ""
    var email: String=""
    var country_code: String=""
    var phone: String=""
    var password: String = ""
    var lat: String = " "
    var lon: String = " "

    var type: String=""
    var national_id: String=""
    var countryId: String=""
    var cityId: String=""
    var nationality_id: String=""
    var address: String=""

     var service_id: String=""
    var previous_experience: String=""
    var years_experience: String=""
    var hour_price: String=""
     var photo: File? = null
    var personal_id_photo: File? = null
    var account_number: String=""
    var account_name: String=""
    var bank_id: String=""
    var iban_number: String=""
     var sub_service_id: ArrayList<Int> = arrayListOf()

    fun validateBankRegisteration(
        account_number: String, account_name: String, bank_id: String, iban_number: String
    ): Boolean {
        return if (account_number.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_account_number)))
            false
        } else if (account_name.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_account_name)))
            false
        } else if (bank_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_bank_id)))
            false
        } else if (iban_number.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_iban_number)))
            false
        } else {

            this.account_name = account_name
            this.bank_id = bank_id
            this.iban_number = iban_number
            this.account_number = account_number
            register()
            true
        }
    }

    fun validateRegisteration(
        name: String,
        national_id: String,
        countryId: String,
        cityId: String,
        nationality_id: String,
        lat: Double?,
        lon: Double?,
        address: String?,
        sub_service_id: ArrayList<Int> = arrayListOf(),
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
        } else if (countryId.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_country_id)))
            false
        } else if (cityId.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_cityid)))
            false
        } else if (nationality_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_nationality_id)))
            false
        } else if (lat.isNull() || lon?.equals(0) == true || address.isNullOrEmpty()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.enter_your_location)))
            false
        } else if (sub_service_id.isNullOrEmpty()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_sub_service)))
            false
        } else if (phone.isNullOrBlank()) {
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
        } else if (hour_price.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_hour_price)))
            false
        } else if (personal_id_photo.isNull()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_personal_id_photo)))
            false
        } else if (photo.isNull()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_photo)))
            false
        } else if (pass.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_password)))
            false
        } else {

            this.name = name
            this.phone = phone
            this.type = type
            this.email = email
            this.country_code = countryCode
            this.countryId = countryId
            this.cityId = cityId
            this.national_id = national_id
            this.nationality_id = nationality_id
            this.password = pass
            this.lon = lon.toString()
            this.lat = lat.toString()
            this.address = address
            this.sub_service_id = sub_service_id
            this.service_id = service_id
            this.previous_experience = previous_experience
            this.years_experience = years_experience
            this.hour_price = hour_price
            this.photo = photo
            this.personal_id_photo = personal_id_photo

         /*   register(
                RegisterParams(
                    name,
                    type,
                    national_id,
                    countryId,
                    cityId,
                    nationality_id,
                    address,
                    lat.toString(),
                    lon.toString(),
                    phone,
                    email,
                    countryCode,
                    service_id,
                    previous_experience,
                    years_experience,
                    hour_price,
                    pass,
                    photo,
                    personal_id_photo,
                    account_number,
                    account_name,
                    bank_id,
                    iban_number,
                    mobile_id,
                    sub_service_id,
                )*/
            produce(AuthAction.RegisterationValidationSucess(true))

                true

      }
    }


    fun isValidParams(countryCode: String,phone: String?, pass: String?): Boolean {

        return if (phone.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.please_enter_your_phone)))
            false
        } else if (pass.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_password)))
            false
        } else {
            login(countryCode,phone, pass)
            true
        }

    }


    fun login(countryCode: String,phone: String, pass: String) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(AuthAction.ShowLoading(true))
            authUserCase.invoke(
                viewModelScope, LoginParms(
                    countryCode,    phone, pass
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
     ) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {
            produce(AuthAction.ShowLoading(true))

            viewModelScope.launch {
                var res = authUserCase.invoke(
                    viewModelScope, RegisterParams(
                            name,type,
                            national_id,
                            countryId,
                            cityId,
                            nationality_id,
                            address,
                            lat,
                            lon,
                            phone,
                            email,
                            country_code,
                            service_id,
                            previous_experience,
                            years_experience,
                            hour_price,
                        password,
                        photo!!,
                        personal_id_photo!!,
                            account_number.trim(),
                            account_name,
                            bank_id,
                            iban_number.trim(),
                            "0",
                            sub_service_id,

                    )
                )     { res ->
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
  fun confirmPhone(  country_code: String,phone: String) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(AuthAction.ShowLoading(true))
            authUserCase.invoke(
                viewModelScope, ConfirmPhoneParams(phone, country_code )
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(AuthAction.ShowPhoneConfirmed(res.data.data as ConfrmPhoneResponse))
                    }
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun getAllCitiesByCountryId(country_id: String, type: Int) {


        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(AuthAction.ShowLoading(true))
            authUserCase.invoke(
                viewModelScope, CityParams(country_id)
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(AuthAction.ShowAllCities(res.data.data as CitesResponse, type))

                    }
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun getAllCountry(type: Int) {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(AuthAction.ShowLoading(true))
            authUserCase.invoke(
                viewModelScope
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(AuthAction.ShowAllCountry(res.data.data as CountriesResponse, type))

                    }
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun getProfile() {
        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


            produce(AuthAction.ShowLoading(true))
            useCaseProfile.invoke(
                viewModelScope
            ) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(AuthAction.ShowProfile(res.data.data as ProfileResponse))

                    }
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }
     fun getSubService(serviceId: String) {

        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {
            usecaseSubService.invoke(
                viewModelScope, SubServicesParams(serviceId)
            ) { data ->
                viewModelScope.launch {
                    produce(AuthAction.ShowSubService(data))
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun getAllServices() {

        if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {
            usecaseService.invoke(
                viewModelScope, PagingParams()
            ) { data ->
                viewModelScope.launch {
                    produce(AuthAction.ShowService(data))
                }
            }
        } else {
            produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
        }
    }

    fun deleteAccount() {

        produce(AuthAction.ShowLoading(true))

        viewModelScope.launch {
            var res = useCaseProfile.invoke(viewModelScope, DELETE_ACCOUNT) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(
                            AuthAction.DeleteAccount(res.data.message)
                        )

                    }
                }

            }

        }
    }
    fun getReviews() {

        produce(AuthAction.ShowLoading(true))

        viewModelScope.launch {
            var res = usecasereviews.invoke(viewModelScope ) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(
                            AuthAction.ShowReviews(res.data.data as ReviewsResponse)
                        )

                    }
                }

            }

        }
    }

    /*    val name: String,
        val nationalcard_id: String,
        val countryId: String="",
        val cityId: String="",
        val nationality_id: String="",
        val address: String="",
        val lat: String,
        val lon: String,
        val email: String,
        val phone: String,
        val country_code: String,
        var service_id: String,
        var previous_experience: String,
        var years_experience: String,
         var photo: File,
        var account_number: String,
        var account_name: String,
        var bank_id: String,
        var iban_number: String,
        var mobile_id: String = "0",
        var sub_service_id: ArrayList<String>*/
    fun validateUpdateProfile(
        name: String,
        nationalcard_id: String?,
        countryId: String?,
        cityId: String?,
        nationality_id: String?,
        address: String? ,
        lat: String?,
        lon: String?,
        email: String,
        phone: String,
        country_code: String,
        service_id: String,
        previous_experience: String,
        years_experience: String,
        hour_price: String,
        photo: File?,
        account_number: String,
        account_name: String,
        bank_id: String,
        iban_number: String,
         sub_service_id: ArrayList<Int>

    ): Boolean {
        return if (name.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_user_name)))
            false
        } else if (nationalcard_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_nationalcard_id)))
            false
        } else if (phone.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_num)))
            false
        } else if (email.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_email)))
            false
        } else if (!Extension.isEmailValid(email)) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.msg_invalide_email)))
            false
        } else if (country_code.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_num_code)))
            false
        } else if (countryId.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_country_id)))
            false
        } else if (cityId.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_cityid)))
            false
        } else if (service_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_service_id)))
            false
        } else if (nationality_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_nationality_id)))
            false
        } else if (lat.isNull() || lon?.equals(0) == true || address.isNullOrEmpty()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.enter_your_location)))
            false
        }
     else if (years_experience.isNullOrBlank())
    {
        produce(AuthAction.ShowFailureMsg(getString(R.string.empty_years_of_experience)))
        false
    } else if (previous_experience.isNullOrBlank())
    {
        produce(AuthAction.ShowFailureMsg(getString(R.string.emptypervious_exper)))
        false
    } else if (hour_price.isNullOrBlank())
    {
        produce(AuthAction.ShowFailureMsg(getString(R.string.empty_hour_price)))
        false
    }
       else if (account_number.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_account_number)))
            false
        } else if (account_name.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_account_name)))
            false
        } else if (bank_id.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_bank_id)))
            false
        } else if (iban_number.isNullOrBlank()) {
            produce(AuthAction.ShowFailureMsg(getString(R.string.empty_iban_number)))
            false
        }

        else
    {

        produce(AuthAction.ProfileUPdatedVaild(   EditProfileParams(
            name ,
            nationalcard_id ,null,
            countryId ,
            cityId ,
            nationality_id ,
            address ,
            lat.toString(),
            lon.toString(),
            email ,
            phone ,
            country_code ,
            service_id ,
            previous_experience ,
            years_experience ,
            hour_price ,
            photo ,
            account_number  ,
            account_name   ,
            bank_id  ,
            iban_number ,
            "0",
            sub_service_id    )))


        true

    }
}

fun updateProfile(param: EditProfileParams) {
    viewModelScope.launch {
        var res = useCaseProfile.invoke(viewModelScope, param) { res ->
            when (res) {
                is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message))
                is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(AuthAction.ShowUserUpdated(res.data.message as String))
                    PrefsHelper.saveUserData(res.data.data as UserDataResponse)

                }
            }
        }
    }
}
fun isValidParamsChangePass(pass: String, newpass: String, confirmpass: String) {
    if (pass.isNullOrBlank()) {
        produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_password)))
        false
    } else if (newpass.isNullOrBlank()) {
        produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_new_password)))
        false

    } else if (confirmpass.isNullOrBlank()) {
        produce(AuthAction.ShowFailureMsg(getString(R.string.msg_empty_confirm_new_password)))
        false

    } else if (!confirmpass.equals(newpass)) {
        produce(AuthAction.ShowFailureMsg(getString(R.string.password_not_matching)))
        false

    } else changePass(ChangePasswordParam(pass, newpass))


}

fun changePass(param: ChangePasswordParam) {
    if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


        produce(AuthAction.ShowLoading(true))
        useCaseProfile.invoke(
            viewModelScope, param
        ) { res ->
            when (res) {
                is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(
                        AuthAction.ChangedPassword(
                            res.data.message as String
                        )
                    )
                }
            }
        }
    } else {
        produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
    }
}
fun getBanks( ) {
    if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


        produce(AuthAction.ShowLoading(true))
        useCaseProfile.invoke(
            viewModelScope, ProfileUseCase.GET_BANKS
        ) { res ->
            when (res) {
                is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(
                        AuthAction.ShowBanks(
                            res.data.data as BanksResponse
                        )
                    )
                }
            }
        }
    } else {
        produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
    }
}
    fun getNationalities( ) {
    if (app?.let { it1 -> NetworkConnectivity.hasInternetConnection(it1) } == true) {


        produce(AuthAction.ShowLoading(true))
        useCaseProfile.invoke(
            viewModelScope, ProfileUseCase.GET_NATIONALITIES
        ) { res ->
            when (res) {
                is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message.toString()))
                is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                is Resource.Success -> {
                    produce(
                        AuthAction.ShowNationalities(
                            res.data.data as NationalitiesResponse
                        )
                    )
                }
            }
        }
    } else {
        produce(AuthAction.ShowFailureMsg(getString(R.string.no_internet)))
    }
}

    fun updateBalance (param: UpdateBalanceParam) {
        produce(AuthAction.ShowLoading(true))

        viewModelScope.launch {
            var res = useCaseProfile.invoke(viewModelScope, param) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> {
                        produce(AuthAction.ShowBalance(res?.data?.message.toString()))
                    }
                }
            }

        }
    }
       fun withdrawBalance (param: WithdrawBalanceParam) {
        produce(AuthAction.ShowLoading(true))

        viewModelScope.launch {
            var res = useCaseProfile.invoke(viewModelScope, param) { res ->
                when (res) {
                    is Resource.Failure -> produce(AuthAction.ShowFailureMsg(res.message))
                    is Resource.Progress -> produce(AuthAction.ShowLoading(res.loading))
                    is Resource.Success -> { produce(AuthAction.ShowBalance(res?.data?.message.toString()))
                    }
                }
            }

        }

    }

}



