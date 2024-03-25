package com.dot_jo.professionworker.ui.fragment.login

import android.graphics.Paint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.base.BaseFragment
import com.dot_jo.professionworker.databinding.FragmentLoginBinding
import com.dot_jo.professionworker.ui.activity.MainActivity
import com.dot_jo.professionworker.util.ext.hideKeyboard
import com.dot_jo.professionworker.util.ext.showActivity
import com.dot_jo.professionworker.util.observe
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