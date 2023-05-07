package com.example.professionworker .ui.fragment.login

  import androidx.paging.PagingData
  import com.example.profession.data.dataSource.response.*
  import com.example.professionworker.base.Action
 import com.example.professionworker.domain.response.UserDataResponse



sealed class AuthAction() : Action {
    data class  LoginSuccess(val data: UserDataResponse): AuthAction()
    data class  RegisterationSuccess(val data: UserDataResponse)  : AuthAction()
    data class  RegisterationValidationSucess(val done: Boolean)  : AuthAction()
    data class  ShowProfile(val data: ProfileResponse)  : AuthAction()
    data class  ShowBanks(val data: BanksResponse)  : AuthAction()
    data class  ShowNationalities(val data: NationalitiesResponse)  : AuthAction()

    data class ShowLoading(val show: Boolean) : AuthAction()
    data class ShowFailureMsg(val message: String?) : AuthAction()
    data class ShowForgetPassword(val message: String?) : AuthAction()
    data class DeleteAccount(val message: String?) : AuthAction()
    data class ShowUserUpdated(val message: String?) : AuthAction()
    data class ChangedPassword(val message: String?) : AuthAction()
    data class ShowAllCities(var data: CitesResponse, val type:Int) : AuthAction()
    data class ShowAllCountry(var data: CountriesResponse, val type:Int) : AuthAction()
    data  class ShowService(val data: PagingData<ServicesItemsResponse>) : AuthAction()
    data  class ShowSubService(val data: PagingData<SubServiceItemsResponse>) : AuthAction()


}
