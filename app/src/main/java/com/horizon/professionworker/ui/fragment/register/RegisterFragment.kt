package com.horizon.professionworker.ui.fragment.register

import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
 import com.horizon.professionworker.R
import com.horizon.professionworker.base.BaseFragment
import com.horizon.professionworker.data.params.AddressParams
import com.horizon.professionworker.data.response.BankItemResponse
import com.horizon.professionworker.data.response.CitesItemsResponse
import com.horizon.professionworker.data.response.NationalitiesItemResponse
import com.horizon.professionworker.data.response.ServicesItemsResponse
import com.horizon.professionworker.data.response.SubServiceItemsResponse
import com.horizon.professionworker.databinding.FragmentRegisterBinding
import com.horizon.professionworker.ui.activity.MainActivity
import com.horizon.professionworker.ui.adapter.CitesListener
import com.horizon.professionworker.ui.adapter.NationalitiesListener
import com.horizon.professionworker.ui.bottomShet.RightAndTermsBottomSheet
import com.horizon.professionworker.ui.dialog.*
import com.horizon.professionworker.ui.fragment.MapBottomSheet
import com.horizon.professionworker.ui.fragment.login.AuthAction
import com.horizon.professionworker.ui.fragment.login.AuthViewModel
import com.horizon.professionworker.ui.fragment.onLocationClick
import com.horizon.professionworker.ui.fragment.profile.ProfileFragment
import com.horizon.professionworker.ui.listener.ServiceOnClickListener
import com.horizon.professionworker.ui.listener.SubServiceListener
import com.horizon.professionworker.util.*
import com.horizon.professionworker.util.ext.hideKeyboard
import com.horizon.professionworker.util.ext.isNull
import com.horizon.professionworker.util.ext.loadImage
import com.horizon.professionworker.util.ext.showActivity
 import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(),
    CountryCodePicker.OnCountryChangeListener {

    private val mViewModel: AuthViewModel by activityViewModels()
    var state = 1
    var cityID: String = ""
    var bankId = ""
    var countryId: String = ""
    var serviceId: String = ""
    var subServicesIdS: ArrayList<Int> = arrayListOf()
    var subServicesNames: ArrayList<String> = arrayListOf()
    var lat: Double? = null
    var long: Double? = null
    var address: String? = null
    var type: Int = -1 // for determine which photo selected
    var providerType: String? = ""
    var verified_countryCode = ""
    var verified_phone: String? = null
    override fun onFragmentReady() {
        onClick()
        onBack()
        stateOne()
        mViewModel.apply {
            observe(viewState) {
                handleViewState(it)
            }
        }
    }

    private fun handleViewState(action: AuthAction) {
        when (action) {
            is AuthAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }
            is AuthAction.RegisterationSuccess -> {
                showProgress(false)
                showActivity(MainActivity::class.java, clearAllStack = true)
            }
            is AuthAction.RegisterationValidationSucess -> {
                showProgress(false)
                mViewModel.confirmPhone(  countryCode.toString(),
                   mViewModel.phone.toString(),)
                showProgress(false)
           //
             }

            is AuthAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }


            is AuthAction.ShowPhoneConfirmed -> {
                showProgress(false)
                if (action.data?.exists == 1) {
                    showToast(resources.getString(R.string.phone_exist_already))
                } else {

                    if (verified_phone.isNullOrEmpty() || verified_phone == null) {
                        CheckOtpSheetFragment.newInstance(
                            mViewModel.country_code.toString(),
                            mViewModel.phone.toString(),
                            object : OnPhoneCheckedWithOtp {
                                override fun onClick(
                                    country_code: String, phone: String, verifed: Boolean
                                ) {
                                    verified_phone = phone
                                    verified_countryCode = country_code
                                    stateThird()                                }
                            }).show(
                            childFragmentManager, "CheckOtpSheetFragment"
                        )

                    } else {
                        if (verified_phone == mViewModel .phone && verified_countryCode == mViewModel.  country_code) {
                            stateThird()                                }
                        else {
                            mViewModel.  phone?.let {
                                CheckOtpSheetFragment.newInstance(
                                    mViewModel.country_code.toString(),
                                    it,
                                    object : OnPhoneCheckedWithOtp {
                                        override fun onClick(
                                            country_code: String, phone: String, verifed: Boolean
                                        ) {
                                            verified_phone = phone
                                            verified_countryCode = country_code
                                            stateThird()                                        }
                                    }).show(
                                    childFragmentManager, "CheckOtpSheetFragment"
                                )
                            }
                        }
                    }
                }
            }

            is AuthAction.ShowAllCities -> {
                showProgress(false)
                action.data.cities?.let { openCitiesDialog(it) }
            }

            is AuthAction.ShowAllCountry -> {
                showProgress(false)
                action.data.countries?.let { openCountriesDialog(it) }
            }
            is AuthAction.ShowNationalities -> {
                showProgress(false)
                action.data.nationalities?.let { openNationalitiesDialog(it) }
            }

            is AuthAction.ShowBanks -> {

                action.data.banks?.let {

                    openBanksDialog(it)


                }
            }
            else -> {

            }
        }
    }

    fun openBanksDialog(data: ArrayList<BankItemResponse>) {
        BanksDialog.newInstance(object : BankListener {
            override fun onBankClicked(item: BankItemResponse?) {
                bankId = item?.id.toString()

                binding.etBank.setText(item?.name)
            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }

    private fun openCountriesDialog(data: ArrayList<CitesItemsResponse>) {
        binding.etCity.setText("")
        cityID = ""
        CategoriesDialog.newInstance(object : CitesListener {
            override fun onOrderClicked(item: CitesItemsResponse?) {
                binding.etCoutry.setText(item?.name)
                (item?.id)?.let { countryId = it }
            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }


    fun openCitiesDialog(data: ArrayList<CitesItemsResponse>) {
        CategoriesDialog.newInstance(object : CitesListener {
            override fun onOrderClicked(item: CitesItemsResponse?) {
                binding.etCity.setText(item?.name)
                (item?.id)?.let { cityID = it }
            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }

    @Inject
    lateinit var permissionManager: PermissionManager
    private fun onBack() {
        activity?.let {
            requireActivity().onBackPressedDispatcher.addCallback(it,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        when (state) {

                            2 -> stateOne()
                            3 -> stateSec()
                            else -> {

                                if (isEnabled) {
                                    isEnabled = false
                                    activity?.let {
                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            }

                                }
                            }

                        }
                    }
                })


        }
    }

    var countryCode = "+966"
    private fun onClick() {

        binding.countryCodePicker.setOnCountryChangeListener(this)
        binding.etBank.setOnClickListener {

            mViewModel.getBanks()
        }
        binding.lytHeader.ivBack.setOnClickListener {

            when (state) {

                2 -> stateOne()
                3 -> stateSec()
                else -> {


findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }

            }        }
        binding.btnRegisterBank.setOnClickListener {

            mViewModel.validateBankRegisteration(
                binding.etAccountId.text.toString(),
                binding.etAccountName.text.toString(), bankId, binding.etIpan.text.toString(),
            )
        }
        binding.terms.text =
            HtmlCompat.fromHtml(getString(R.string.some_text), HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.btnRegister.setOnClickListener {

            isVaildRegisterationData()
        }


        binding.etCity.setOnClickListener {
            if (countryId == "") showToast(resources.getString(R.string.choose_country_first))
            else mViewModel.getAllCitiesByCountryId(
                countryId.toString(), ProfileFragment.getCurrentCountryName
            )
        }
        binding.etCoutry.setOnClickListener {
            mViewModel.getAllCountry(ProfileFragment.getAllCountries)
        }
        binding.etServiceDetails.setOnClickListener {
            if (serviceId.isNullOrBlank()) showToast(resources.getString(R.string.please_select_servie))
            else openSubServicesDialog()
        }
        binding.etJob.setOnClickListener {

            openServicesDialog()
        }
        binding.etNationality.setOnClickListener {
            mViewModel.getNationalities()
        }
        binding.etAccountType.setOnClickListener {
            showProviderTypeSheetFragment()
         }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }
        binding.btnSignIn2.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }
        binding.btnNext1.setOnClickListener {
            if (providerType.isNullOrBlank() == true) showToast(resources.getString(R.string.please_choose_account_type))
            else {
                mViewModel.type = providerType as String
                stateSec()

            }

        }
        binding.terms.setOnClickListener {
            RightAndTermsBottomSheet.newInstance( ).show(childFragmentManager, RightAndTermsBottomSheet::class.java.canonicalName)
          //  findNavController().navigate(R.id.rightAndTermsBottomSheet)
        }
        binding.etLocation.setOnClickListener {
            checkLocation()
        }
        binding.lytIdIamge.setOnClickListener {
            type = 1
            pickImage()
        }
        binding.lytPersonalImg.setOnClickListener {
            type = 2
            pickImage()
        }
        binding.lytHeader.ivBack.setOnClickListener {
            if (state == 2) stateOne()
            else {

                activity?.onBackPressed()
            }
        }
        binding.btnSignIn.setPaintFlags(binding.btnSignIn.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

    private fun openServicesDialog() {
        ServicesDialog.newInstance(object : ServiceOnClickListener {


            override fun onServiceClickListener(item: ServicesItemsResponse?) {
                subServicesIdS = arrayListOf()
                subServicesNames = arrayListOf()
                binding.etServiceDetails.setText("")
                binding.etJob.setText(item?.name)
                (item?.id)?.let { serviceId = it.toString() }
            }


        }).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }


    private fun openSubServicesDialog() {
        SubServicesDialog.newInstance(object : SubServiceListener {


            override fun onSubServiceClickListener(items: ArrayList<SubServiceItemsResponse>) {
                subServicesIdS = arrayListOf()
                subServicesNames = arrayListOf()
                for (i in items) {
                    i.id?.let { subServicesIdS.add(it) }
                    subServicesNames.add(i.name.toString())
                }
                binding.etServiceDetails.setText(subServicesNames.toString())
            }

        }, serviceId).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }

    var nationality_id: String = ""

    fun openNationalitiesDialog(data: ArrayList<NationalitiesItemResponse>) {
        NationalitiesDialog.newInstance(object : NationalitiesListener {

            override fun onItemClicked(item: NationalitiesItemResponse?) {
                nationality_id = item?.id.toString()
                binding.etNationality.setText(item?.name)
            }


        }, data).show(childFragmentManager, CategoriesDialog::class.java.canonicalName)
    }

    private fun isVaildRegisterationData() {
        mViewModel.validateRegisteration(
            binding.etUserName.text.toString(),
            binding.etId.text.toString(),
            countryId,
            cityID,
            nationality_id,
            lat, long, address,
            subServicesIdS, countryCode,

            binding.etEmail.text.toString(),
            binding.etPhone.text.toString(),
            serviceId,
            binding.etExExperience.text.toString(),
            binding.etExperienceYears.text.toString(),
            binding.etPaymentPerHour.text.toString(),
            photo, personal_id_photo,
            binding.etPassword.text.toString(),


            )
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

    var photo: File? = null
    var personal_id_photo: File? = null

    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                FileManager.from(requireActivity(), it)?.let { file ->
                    if (type == 1) {
                        personal_id_photo = file
                        binding.ivIdImg.loadImage(file, isCircular = true)

                    }
                    else {
                        photo = file
                        binding.ivPersonalImg.loadImage(file, isCircular = true)
                    }
                    //
                }
            }
        }

    override fun onCountrySelected() {
        countryCode = "+" + binding.countryCodePicker.selectedCountryCode
    }

    private fun stateSec() {
        state = 2
        binding.lytData1.isVisible = false
        binding.lytData3.isVisible = false
        binding.lytData2.isVisible = true
        binding.view4.isVisible = true
        binding.view5.isVisible = false

    }

    private fun stateThird() {
        state = 3
        binding.lytData1.isVisible = false
        binding.lytData2.isVisible = false
        binding.lytData3.isVisible = true
        binding.view4.isVisible = true
        binding.view5.isVisible = true

    }

    private fun stateOne() {
        state = 1
        binding.lytData1.isVisible = true
        binding.lytData2.isVisible = false
        binding.lytData3.isVisible = false
        binding.view5.isVisible = false
        binding.view4.isVisible = false

    }

    private fun showProviderTypeSheetFragment() {
        ProviderTypeSheetFragment.newInstance(object : OnProviderClick {
            override fun onClick(choice: String) {
                if (choice.equals(Constants.PERSON)) {
                    providerType = Constants.PERSON
                    mViewModel.type = Constants.PERSON
                    binding.etAccountType.setText(resources.getString(R.string.person))

                } else if (choice.equals(Constants.COMPANY)) {
                    providerType = Constants.COMPANY
                    mViewModel.type = Constants.COMPANY
                    binding.etAccountType.setText(resources.getString(R.string.company))

                }
            }


        }).show(childFragmentManager, DeleteAccountSheetFragment::class.java.canonicalName)
    }

    private fun checkLocation() {
        if (permissionManager.hasAllLocationPermissions()) {
            checkIfLocationEnabled()
        } else {
            permissionsLauncher?.launch(permissionManager.getAllLocationPermissions())
        }
    }

    private val locationSettingLauncher = openLocationSettingsResultLauncher {
        checkIfLocationEnabled()
    }

    @Inject
    lateinit var locationManager: WWLocationManager
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
                this@RegisterFragment.lat = lat
                this@RegisterFragment.long = long
                this@RegisterFragment.address = address?.address
                if (!address.isNull()) {
                    binding.etLocation.visibility = View.VISIBLE
                    binding.etLocation.setText(address?.address.toString())
                } else {
                    binding.etLocation.visibility = View.GONE
                }
            }
        }).show(childFragmentManager, MapBottomSheet::class.java.canonicalName)
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


}