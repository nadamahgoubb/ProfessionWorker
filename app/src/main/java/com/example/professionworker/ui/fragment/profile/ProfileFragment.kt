package com.example.professionworker.ui.fragment.profile

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.profession.data.dataSource.response.*
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.data.params.AddressParams
import com.example.professionworker.data.repo.PrefsHelper
 import com.example.professionworker.databinding.FragmentProfileBinding
import com.example.professionworker.ui.activity.AuthActivity
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.ui.adapter.CitesListener
import com.example.professionworker.ui.adapter.NationalitiesListener
import com.example.professionworker.ui.dialog.*
import com.example.professionworker.ui.fragment.MapBottomSheet
import com.example.professionworker.ui.fragment.login.AuthViewModel
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.ui.fragment.login.AuthAction
import com.example.professionworker.ui.fragment.onLocationClick
import com.example.professionworker.ui.listener.ServiceOnClickListener
import com.example.professionworker.ui.listener.SubServiceListener
import com.example.professionworker.util.*
import com.example.professionworker.util.ext.isNull
import com.example.professionworker.util.ext.loadImage
import com.example.professionworker.util.ext.roundTo
import com.google.android.material.appbar.AppBarLayout
import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(),
    CountryCodePicker.OnCountryChangeListener {
    companion object {
        val getCurrentCountryName = 1
        val getAllCountries = 2
    }

    private lateinit var parent: MainActivity
    private val mViewModel: AuthViewModel by viewModels()
    var cityID: String = ""
    var countryId: String = ""
    var nationality_id: String = ""
    var serviceId: String = ""
    var bankId: String = ""
    var subServicesIdS: ArrayList<Int> = arrayListOf()
    var subServicesNames: ArrayList<String> = arrayListOf()

    var countryCode: String = "+966"
     var   expernenceYesars =""
     var   hourPrice =""
    var countries: ArrayList<CitesItemsResponse> = arrayListOf()
    var cities: ArrayList<CitesItemsResponse> = arrayListOf()

    var lat: String? = null
    var long: String? = null
    var address: String? = null

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var locationManager: WWLocationManager
    var state = 0 // show data  1->edit


    override fun onFragmentReady() {

        onClick()
        setupUi()
        mViewModel.apply {
            getProfile()
            observe(viewState) {
                handleViewState(it)
            }
        }
        binding.swiperefresh.setOnRefreshListener {
            mViewModel.getProfile()
            if (binding.swiperefresh != null) binding.swiperefresh.isRefreshing = false
        }
    }

    private fun onClick() {

        binding.countryCodePicker.setOnCountryChangeListener(this)
        binding.btnDelete.setOnClickListener {
            showDeletBotttomSheetFragment()
        }
        binding.lytRate.setOnClickListener {
            findNavController().navigate(R.id.reviewFragment)
        }

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.btnAddWallet.setOnClickListener {
            showAddBalanceSheetFragment()
        }
binding.btnChangepass.setOnClickListener {
findNavController().navigate(R.id.changePassFragment)}
        binding.btnEdit.setOnClickListener {
            if (state == 0) stateEditProfile()
            else mViewModel.validateUpdateProfile(
                binding.etUserName.text.toString(),
                binding.etId.text.toString(),
                countryId.toString(),
                cityID.toString(),
                nationality_id, address,
                lat.toString(),
                long.toString(),
                binding.etEmail.text.toString(),
                binding.etPhone.text.toString(),
                countryCode,
                serviceId,
                binding.etExExperience.text.toString(),
             expernenceYesars,
                hourPrice,
                image,
                binding.etAccountId.text.toString(),
                binding.etAccountName.text.toString(),
                bankId, binding.etIpan.text.toString(),
                subServicesIdS,


                )
        }
        binding.etCoutry.setOnClickListener {
            if (state == 1) {
                if (countries.isNullOrEmpty()) mViewModel.getAllCountry(getAllCountries)
                else openCountriesDialog(countries)

            }
        }

        binding.etCity.setOnClickListener {
            if (state == 1) {
                if (cities.isNullOrEmpty()) mViewModel.getAllCitiesByCountryId(
                    countryId, getAllCountries
                )
                else if (countryId == "") showToast(resources.getString(R.string.choose_country_first))
                else openCitiesDialog(cities)
            }
        }
        binding.etLocation.setOnClickListener {
            checkLocation()
        }

        binding.etCity.setOnClickListener {
            if (countryId == "")  showToast(resources.getString(R.string.choose_country_first))
            else  mViewModel.getAllCitiesByCountryId(
                countryId.toString(),
                ProfileFragment.getCurrentCountryName
            )
        }
        binding.etCoutry.setOnClickListener {
            mViewModel.getAllCountry(ProfileFragment.getAllCountries)
        }
        binding.etServiceDetails.setOnClickListener {
            if(serviceId.isNullOrBlank()) showToast(resources.getString(R.string.please_select_servie))
            else  openSubServicesDialog()
        }
        binding.etJob.setOnClickListener {

            openServicesDialog()
        }
  binding.etNationality.setOnClickListener {

            mViewModel.getNationalities()
        }
  binding.etBank.setOnClickListener {

            mViewModel.getBanks()
        }


        binding.ivEdit.setOnClickListener {

            pickImage()
        }

    }
    private fun openServicesDialog( ) {
        ServicesDialog.newInstance(object : ServiceOnClickListener {


            override fun onServiceClickListener(item: ServicesItemsResponse?) {
                subServicesIdS= arrayListOf()
                binding.etServiceDetails.setText("")
                binding.etJob.setText(item?.name)
                (item?.id)?.let { serviceId = it.toString() }             }


        } ).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }
    private fun openSubServicesDialog( ) {
        SubServicesDialog.newInstance(object : SubServiceListener {


            override fun onSubServiceClickListener(items: ArrayList<SubServiceItemsResponse>) {
                subServicesIdS= arrayListOf()
                subServicesNames= arrayListOf()
                for (i in items) {
                    i.id?.let { subServicesIdS.add(it) }
                    i.name?.let { subServicesNames.add(it) }

                }
                binding.etServiceDetails.setText(subServicesNames.toString())
            }

        } , serviceId).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }
    fun stateShowData() {
        state = 0
        binding.etUserName.isEnabled = false
        binding.etPhone.isEnabled = false
        binding.etCity.isEnabled = false
        binding.etCoutry.isEnabled = false
        binding.etEmail.isEnabled = false
        binding.etLocation.isEnabled = false
        binding.ivEdit.isVisible = false

        binding.etId.isEnabled = false
        binding.etNationality.isEnabled = false
        binding.etJob.isEnabled = false
        binding.etServiceDetails.isEnabled = false
        binding.etExperienceYears.isEnabled = false
        binding.etExExperience.isEnabled = false
        binding.etPaymentPerHour.isEnabled = false
        binding.etBank.isEnabled = false
        binding.etAccountId.isEnabled = false
        binding.etAccountName.isEnabled = false
        binding.etIpan.isEnabled = false

        binding.etUserName.setTextColor(resources.getColor(R.color.grey_700))
        binding.etPhone.setTextColor(resources.getColor(R.color.grey_700))
        binding.etCity.setTextColor(resources.getColor(R.color.grey_700))
        binding.etCoutry.setTextColor(resources.getColor(R.color.grey_700))
        binding.etEmail.setTextColor(resources.getColor(R.color.grey_700))
        binding.etLocation.setTextColor(resources.getColor(R.color.grey_700))


        binding.etId.setTextColor(resources.getColor(R.color.grey_700))
        binding.etNationality.setTextColor(resources.getColor(R.color.grey_700))
        binding.etJob.setTextColor(resources.getColor(R.color.grey_700))
        binding.etServiceDetails.setTextColor(resources.getColor(R.color.grey_700))
        binding.etExperienceYears.setTextColor(resources.getColor(R.color.grey_700))
        binding.etExExperience.setTextColor(resources.getColor(R.color.grey_700))
        binding.etPaymentPerHour.setTextColor(resources.getColor(R.color.grey_700))
        binding.etBank.setTextColor(resources.getColor(R.color.grey_700))
        binding.etAccountId.setTextColor(resources.getColor(R.color.grey_700))
        binding.etAccountName.setTextColor(resources.getColor(R.color.grey_700))
        binding.etIpan.setTextColor(resources.getColor(R.color.grey_700))
        binding.btnEdit.setText(resources.getText(R.string.edit))
    }

    fun stateEditProfile() {
        state = 1
         binding.etUserName.isEnabled = true
        binding.etPhone.isEnabled = true
        binding.etCity.isEnabled = true
        binding.etCoutry.isEnabled = true
        binding.etEmail.isEnabled = true
        binding.etLocation.isEnabled = true
        binding.ivEdit.isVisible = true

        binding.etId.isEnabled = true
        binding.etNationality.isEnabled = true
        binding.etJob.isEnabled = true
        binding.etServiceDetails.isEnabled = true
        binding.etExperienceYears.isEnabled = true
        binding.etExExperience.isEnabled = true
        binding.etPaymentPerHour.isEnabled = true
        binding.etBank.isEnabled = true
        binding.etAccountId.isEnabled = true
        binding.etAccountName.isEnabled = true
        binding.etIpan.isEnabled = true

        binding.etUserName.setTextColor(Color.BLACK)
        binding.etPhone.setTextColor(Color.BLACK)
        binding.etCity.setTextColor(Color.BLACK)
        binding.etCoutry.setTextColor(Color.BLACK)
        binding.etEmail.setTextColor(Color.BLACK)
        binding.etLocation.setTextColor(Color.BLACK)

        binding.etId.setTextColor(Color.BLACK)
        binding.etNationality.setTextColor(Color.BLACK)
        binding.etJob.setTextColor(Color.BLACK)
        binding.etServiceDetails.setTextColor(Color.BLACK)
        binding.etExperienceYears.setTextColor(Color.BLACK)
        binding.etExExperience.setTextColor(Color.BLACK)
        binding.etPaymentPerHour.setTextColor(Color.BLACK)
        binding.etBank.setTextColor(Color.BLACK)
        binding.etAccountId.setTextColor(Color.BLACK)
        binding.etAccountName.setTextColor(Color.BLACK)
        binding.etIpan.setTextColor(Color.BLACK)
        binding.btnEdit.setText(resources.getText(R.string.save))
    }

    private fun handleViewState(action: AuthAction) {
        when (action) {
            is AuthAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }
            is AuthAction.ShowProfile -> {
                showProgress(false)
                showData(action.data)
            }

            is AuthAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }
            is AuthAction.ShowUserUpdated -> action.message?.let {
                showToast(action.message)
                stateShowData()
                mViewModel.getProfile()
            }

            is AuthAction.ShowAllCities -> {
                showProgress(false)

                action.data.cities?.let {
                    cities = it
                    /*    if (action.type == getAllCountries)*/ openCitiesDialog(it)
                    /*   else
                     binding.etCity.setText(searchInCiteies(cityID, cities)?.name)*/

                }
            }
            is AuthAction.ShowBanks -> {

                action.data.banks?.let {

                openBanksDialog(it)


                }
            }   is AuthAction.ShowNationalities -> {

                action.data.nationalities?.let {

                openNationalitiesDialog(it)


                }
            }

            is AuthAction.ShowAllCountry -> {

                showProgress(false)

                action.data.countries?.let {
                    countries = it
                    // if (action.type == getAllCountries)
                    openCountriesDialog(it)
                    //     else binding.etCoutry.setText(searchInCiteies(countryId, countries)?.name)
                }
            }
            is AuthAction.DeleteAccount -> {
                showProgress(false)
                showToast(action.message)
                PrefsHelper.clear()
                var intent = Intent(activity, AuthActivity::class.java)
                intent.putExtra(Constants.Start, Constants.login)
                startActivity(intent)
                activity?.finish()


            }

            else -> {

            }
        }
    }

    private fun checkLocation() {
        if (permissionManager.hasAllLocationPermissions()) {
            checkIfLocationEnabled()
        } else {
            permissionsLauncher?.launch(permissionManager.getAllLocationPermissions())
        }
    }

    private val permissionsLauncher = requestAppPermissions { allIsGranted, _ ->
        if (allIsGranted) {
            checkIfLocationEnabled()
        } else {
            Toast.makeText(
                activity, getString(R.string.not_all_permissions_accepted), Toast.LENGTH_LONG
            ).show()
        }
    }
    private val locationSettingLauncher = openLocationSettingsResultLauncher {
        checkIfLocationEnabled()
    }

    private fun checkIfLocationEnabled() {
        if (locationManager.isLocationEnabled()) {
            openMaps()
        } else {
            activity?.let { locationManager.buildAlertMessageNoGps(it, locationSettingLauncher) }
        }
    }

    private fun openMaps() {
        MapBottomSheet.newInstance(object : onLocationClick {
            override fun onClick(lat: Double?, long: Double?, address: AddressParams?) {
                this@ProfileFragment.lat = lat.toString()
                this@ProfileFragment.long = long.toString()
                this@ProfileFragment.address = address?.address.toString()
                if (!address.isNull()) {
                    binding.etLocation.visibility = View.VISIBLE
                    binding.etLocation.setText(address?.address.toString())
                } else {
                    binding.etLocation.visibility = View.GONE
                }
            }
        }).show(childFragmentManager, MapBottomSheet::class.java.canonicalName)
    }

    private fun openCountriesDialog(data: ArrayList<CitesItemsResponse>) {
        CategoriesDialog.newInstance(object : CitesListener {
            override fun onOrderClicked(item: CitesItemsResponse?) {
                (item?.id)?.let { countryId = it.toString() }
                binding.etCoutry.setText(item?.name)
                binding.etCity.setText("")
                cityID=""

            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }

    private fun showAddBalanceSheetFragment() {
        AddBalanceSheetFragment.newInstance(object : OnClickAddBalance {
            override fun onClick(choice: String) {


            }


        }).show(childFragmentManager, AddBalanceSheetFragment::class.java.canonicalName)
    }

    fun openCitiesDialog(data: ArrayList<CitesItemsResponse>) {
        CategoriesDialog.newInstance(object : CitesListener {
            override fun onOrderClicked(item: CitesItemsResponse?) {
                (item?.id)?.let { cityID = it }
                binding.etCity.setText(item?.name)
            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }
    fun openBanksDialog(data: ArrayList<BankItemResponse>) {
        BanksDialog.newInstance(object : BankListener {
            override fun onBankClicked(item: BankItemResponse?) {
             bankId= item?.id.toString()

                binding.etBank.setText(item?.name)            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }
    fun openNationalitiesDialog(data: ArrayList<NationalitiesItemResponse>) {
        NationalitiesDialog.newInstance(object : NationalitiesListener {

            override fun onItemClicked(item: NationalitiesItemResponse?) {
              nationality_id   = item?.id.toString()
                binding.etNationality.setText(item?.name)            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }

    private fun showData(data: ProfileResponse) {
        stateShowData()
        binding.lytData.isVisible = true
        binding.etUserName.setText(data.name)
        binding.tvName.setText(data.name)
        //   binding.etCity.setText(data.cityName)
        binding.etId.setText(data.nationalId)
        binding.etNationality.setText(data.nationalityName)
        binding.etLocation.setText(data.address)
        binding.etEmail.setText(data.email)
        binding.etPhone.setText(data.phone)
        binding.tvRate.setText(data.avgRate?.toDoubleOrNull()?.roundTo(2).toString())
        binding.tvRateCounts.setText("(" + data.countReviews + ")")
        binding.etExExperience.setText(data.previousExperience)
        binding.etExperienceYears.setText(data.yearsExperience + resources.getString(R.string.years))
        binding.etJob.setText(data.serviceName)
        binding.tvServiceTitle.setText(data.serviceName)

        binding.etPaymentPerHour.setText(
            data.hourPrice.roundTo(2).toString() + resources.getString(
                R.string.sr
            ) + resources.getString(R.string.per_hour)
        )

            expernenceYesars =data.yearsExperience.toString()
            hourPrice =   data.hourPrice.toString()

        binding.etIpan.setText(data.ibanNumber)
        binding.etAccountId.setText(data.accountNumber)
        binding.etAccountName.setText(data.accountName)
        binding.etBank.setText(data.bankName)

        binding.tvBalance.setText(data.balance + resources.getString(R.string.sr))

        this@ProfileFragment.lat = data.lat//?.toDoubleOrNull()
        this@ProfileFragment.long = data.lon//?.toDoubleOrNull()
        this@ProfileFragment.address = data.address
        binding.etLocation.setText(data.address)
        binding.ivProfile.loadImage(data.photo, placeHolderImage = R.drawable.empty_user, isCircular = true, errorImage = R.drawable.empty_user)
        countryCode = data.countryCode.toString()
        binding.countryCodePicker.setDefaultCountryUsingPhoneCode(
            data.countryCode.toString().toInt()
        )
        data.cityId?.let {
            cityID = it
        }
        data.bankId?.let {
            bankId = it
        }
        data.countryId?.let {
            countryId = it
        }
        data.nationalityId?.let {
            nationality_id = it
        }
        serviceId= data.serviceId.toString()
        serviceId= data.serviceId.toString()

        binding.etCity.setText(data.cityName)
        binding.etCoutry.setText(data.countryName)
         for (i in data.subServices) {
             i.id?.let { subServicesIdS.add(it) }
             i.name?.let { subServicesNames.add(it) }

         }
        binding.etServiceDetails.setText(subServicesNames.toString())
        try {
            var country_code= data.countryCode?.substring(1, data.countryCode!!.length).toString()
            country_code?.toInt()?.let { binding.countryCodePicker.setCountryForPhoneCode(it) }

        }catch (e:Exception){

        }
        //  mViewModel.getAllCitiesByCountryId(countryId, getCurrentCountryName)

    }

    fun searchInCiteies(id: String, list: ArrayList<CitesItemsResponse>): CitesItemsResponse? {
        for (i in list) {
            if (id == i.id) return i

        }
        return null
    }

    private fun setupUi() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(true)
        parent.showSideNav(true)
        //    binding.cardSubscribation.tvSubscribe.setPaintFlags(  binding.cardSubscribation.tvSubscribe.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) == binding.appBarLayout.getTotalScrollRange()) {
                // If collapsed, then do this
                binding.ivProfile.setVisibility(View.GONE);
                binding.lytImg.setVisibility(View.GONE);
                binding.lytName.setVisibility(View.GONE);


            } else if (verticalOffset == 0) {
                binding.lytImg.setVisibility(View.VISIBLE);
                binding.ivProfile.setVisibility(View.VISIBLE);
                binding.lytName.setVisibility(View.VISIBLE);
            } else {
                // Somewhere in between
                // Do according to your requirement
            }

        })
    }

    fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun showDeletBotttomSheetFragment() {
        DeleteAccountSheetFragment.newInstance(object : OnClick {
            override fun onClick(choice: String) {
                if (choice.equals(Constants.YES)) {
                    mViewModel.deleteAccount()

                } else {

                }
            }


        }).show(childFragmentManager, DeleteAccountSheetFragment::class.java.canonicalName)
    }

    private val imagePermissionLauncherResult = requestAppPermissions { allIsGranted, _ ->
        if (allIsGranted) {
            FileManager.pickOneImage(this, selectImageFromGalleryResult)
        } else showToast(getString(R.string.not_all_permissions_accepted))
    }

    private fun pickImage() {
        if (permissionManager.hasAllFilePickerPermissions()) {
            FileManager.pickOneImage(this, selectImageFromGalleryResult)
        } else {
            imagePermissionLauncherResult?.launch(permissionManager.getAllImagePermissions())
        }
    }

    var image: File? = null

    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                FileManager.from(requireActivity(), it)?.let { file ->
                    image = file

                    binding.ivProfile.loadImage(file, isCircular = true)
                }
            }
        }

    override fun onCountrySelected() {
        countryCode = "+" + binding.countryCodePicker.selectedCountryCode
    }


}

