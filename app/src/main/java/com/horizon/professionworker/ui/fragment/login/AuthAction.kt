package com.horizon.professionworker .ui.fragment.login

  import androidx.paging.PagingData
  import com.horizon.professionworker.base.Action
  import com.horizon.professionworker.data.params.EditProfileParams
  import com.horizon.professionworker.data.response.BanksResponse
  import com.horizon.professionworker.data.response.CitesResponse
  import com.horizon.professionworker.data.response.ConfrmPhoneResponse
  import com.horizon.professionworker.data.response.CountriesResponse
  import com.horizon.professionworker.data.response.NationalitiesResponse
  import com.horizon.professionworker.data.response.ProfileResponse
  import com.horizon.professionworker.data.response.ServicesItemsResponse
  import com.horizon.professionworker.data.response.SubServiceItemsResponse
  import com.horizon.professionworker.data.response.ReviewsResponse
  import com.horizon.professionworker.data.response.UserDataResponse
 
sealed class AuthAction : Action {
    data class  LoginSuccess(val data: UserDataResponse): AuthAction()
    data class  RegisterationSuccess(val data: UserDataResponse)  : AuthAction()
    data class  RegisterationValidationSucess(val done: Boolean)  : AuthAction()
    data class  ShowProfile(val data: ProfileResponse)  : AuthAction()
    data class  ShowBanks(val data: BanksResponse)  : AuthAction()
    data class  ShowBalance(val message: String): AuthAction()
    data class  ShowNationalities(val data: NationalitiesResponse)  : AuthAction()
    data class  ProfileUPdatedVaild(val data: EditProfileParams)  : AuthAction()

    data class ShowLoading(val show: Boolean) : AuthAction()
    data class ShowFailureMsg(val message: String?) : AuthAction()
    data class ShowForgetPassword(val message: String?) : AuthAction()
    data class  ShowPhoneConfirmed(val data: ConfrmPhoneResponse)  : AuthAction()
    data class DeleteAccount(val message: String?) : AuthAction()
    data class ShowUserUpdated(val message: String?) : AuthAction()
    data class ChangedPassword(val message: String?) : AuthAction()
    data class ShowAllCities(var data: CitesResponse, val type:Int) : AuthAction()
    data class ShowAllCountry(var data: CountriesResponse, val type:Int) : AuthAction()
    data  class ShowService(val data: PagingData<ServicesItemsResponse>) : AuthAction()
    data  class ShowReviews(val data: ReviewsResponse) : AuthAction()
    data  class ShowSubService(val data: PagingData<SubServiceItemsResponse>) : AuthAction()

}
