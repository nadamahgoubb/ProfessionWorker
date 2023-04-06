package com.example.professionworker .ui.fragment.login

  import com.example.profession.data.dataSource.response.CitesResponse
  import com.example.profession.data.dataSource.response.CountriesResponse
  import com.example.professionworker.base.Action
 import com.example.professionworker.data.response.UserDataResponse


/*sealed class AuthAction() : Action {
    data class  LoginSuccess(val data: UserDataResponse): AuthAction()
    data class  RegisterationSuccess(val data: UserDataResponse)  : AuthAction()

     data class ShowLoading(val show: Boolean) : AuthAction()
    data class ShowFailureMsg(val message: String?) : AuthAction()
    data class ShowForgetPassword(val message: String?) : AuthAction()
    *//*data class ShowAllCities(var data: CitesResponse) : AuthAction()
    data class ShowAllCountry(var data: CountriesResponse) : AuthAction()
*//*


}*/

sealed class AuthAction() : Action {
    data class  LoginSuccess(val data: UserDataResponse): AuthAction()
    data class  RegisterationSuccess(val data: UserDataResponse)  : AuthAction()

    data class ShowLoading(val show: Boolean) : AuthAction()
    data class ShowFailureMsg(val message: String?) : AuthAction()
    data class ShowForgetPassword(val message: String?) : AuthAction()
    data class ShowAllCities(var data: CitesResponse) : AuthAction()
    data class ShowAllCountry(var data: CountriesResponse) : AuthAction()



}
