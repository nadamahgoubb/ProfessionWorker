package com.example.professionworker.ui.fragment.login

import android.content.Intent
import android.graphics.Paint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentLoginBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.ext.showActivity
import com.example.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
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
          /*  is AuthAction.ShowAllCities -> {
                showProgress(false)
                action.data.cities?.let { openCitiesDialog(it) }
            }

            is AuthAction.ShowAllCountry -> {
                showProgress(false)
                action.data.countries?.let { openCountriesDialog(it) }
            }*/
            else -> {

            }
        }
    }

    private fun onClick() {
        binding.tvForgetPass.setPaintFlags( binding.tvForgetPass.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.tvCreate.setPaintFlags( binding.tvCreate.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.btnSignIn.setOnClickListener {
            mViewModel.isValidParams(binding.etUserName.text.toString(), binding.etPassword.text.toString())

        }
        binding.tvCreate.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        binding.tvForgetPass.setOnClickListener {
            findNavController().navigate(R.id.forgetPasswordFragment)
        }
    }

}