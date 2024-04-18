package com.horizon.professionworker.ui.fragment.login

import android.graphics.Paint
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hbb20.CountryCodePicker
import com.horizon.professionworker.R
import com.horizon.professionworker.base.BaseFragment
import com.horizon.professionworker.databinding.FragmentLoginBinding
import com.horizon.professionworker.ui.activity.MainActivity
import com.horizon.professionworker.util.ext.hideKeyboard
import com.horizon.professionworker.util.ext.showActivity
import com.horizon.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(),
    CountryCodePicker.OnCountryChangeListener {
    private var countryCode: String = "+966"
    private val mViewModel: AuthViewModel by viewModels()
    override fun onFragmentReady() {
        onClick()
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
            is AuthAction.LoginSuccess -> {
                showProgress(false)
                showActivity(MainActivity::class.java, clearAllStack = true)
            }

            is AuthAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }

            else -> {

            }
        }
    }

    private fun onClick() {
        binding.lytHeader.ivBack.isVisible=false
        binding.countryCodePicker.setOnCountryChangeListener (this)
        binding.tvForgetPass.setPaintFlags( binding.tvForgetPass.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.tvCreate.setPaintFlags( binding.tvCreate.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.btnSignIn.setOnClickListener {
            mViewModel.isValidParams(countryCode,binding.etPhone.text.toString(), binding.etPassword.text.toString())

        }
        binding.tvCreate.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        binding.tvForgetPass.setOnClickListener {
            findNavController().navigate(R.id.forgetPasswordFragment)
        }
    }

    override fun onCountrySelected() {
        countryCode = "+" + binding.countryCodePicker.selectedCountryCode
    }

}