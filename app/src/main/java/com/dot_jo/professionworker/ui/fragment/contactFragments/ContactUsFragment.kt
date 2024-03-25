package com.dot_jo.professionworker.ui.fragment.contactFragments

import android.text.Html
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.base.BaseFragment
import com.dot_jo.professionworker.databinding.FragmentContactUsBinding
import com.dot_jo.professionworker.ui.activity.MainActivity
import com.dot_jo.professionworker.ui.fragment.login.AuthAction
import com.dot_jo.professionworker.util.ext.hideKeyboard
import com.dot_jo.professionworker.util.ext.showActivity
import com.dot_jo.professionworker.util.observe
import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactUsFragment : BaseFragment<FragmentContactUsBinding>() , CountryCodePicker.OnCountryChangeListener {

    private lateinit var parent: MainActivity
    val mViewModel: SupportViewModel by viewModels()
    var countryCode ="+966"
    override fun onFragmentReady() {

        setupUi()
        onClick()
        mViewModel.apply {
            getGoals()
            observe(viewState) {
                handleViewState(it)
            }
        }
        onBack()
    }
    private fun onBack() {
        activity?.let {
            requireActivity().onBackPressedDispatcher.addCallback(it,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {

                        if (isEnabled) {
                            isEnabled = false
                            showActivity(MainActivity::class.java, clearAllStack = true)
                        }

                    }
                })
        }
    }

    private fun handleViewState(action: SupportAction) {
        when (action) {
            is SupportAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }

            is SupportAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }
            is SupportAction.ShowContactSucces -> action.message?.let {
                showToast(action.message)
                showProgress(false)
                activity?.onBackPressed()

            }
            is SupportAction.ShowGoals -> {
                binding.lytData.isVisible=true
                 binding.tvGoals.setText(Html.fromHtml((action.data?.content)))
            }


            else -> {

            }
        }
    }

    private fun onClick() {

        binding.toolbar.ivBack.setOnClickListener {
            showActivity(MainActivity::class.java, clearAllStack = true)
        }

        binding.btnDone.setOnClickListener {
            if(binding.etPhone.text.toString().isEmpty()) showToast(resources.getString(R.string.please_enter_your_phone))
            if(binding.etMsg.text.toString().isEmpty()) showToast(resources.getString(R.string.msg_empty_content))
            else mViewModel.contact(countryCode, binding.etPhone.text.toString(),binding.etMsg.text.toString())
        }
    }


    private fun setupUi() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(false)
        parent.showSideNav(true)
        binding.toolbar.tvTitle.setText(resources.getString(R.string.contact_with_app))
        binding.toolbar.ivMenu.isVisible = true
        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }

    }

    override fun onCountrySelected() {
        countryCode = "+" + binding.countryCodePicker.selectedCountryCode
    }
}