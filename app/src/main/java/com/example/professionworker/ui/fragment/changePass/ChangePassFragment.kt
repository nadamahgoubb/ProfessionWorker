package com.example.professionworker.ui.fragment.changePass

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.data.repo.PrefsHelper
import com.example.professionworker.databinding.FragmentChangePassBinding
import com.example.professionworker.ui.activity.AuthActivity
import com.example.professionworker.ui.fragment.login.AuthAction
import com.example.professionworker.ui.fragment.login.AuthViewModel
import com.example.professionworker.util.Constants
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ChangePassFragment : BaseFragment<FragmentChangePassBinding>() {
    private fun setupToolbar() {
        binding.toolbar.tvTitle.setText(resources.getString(R.string.change_pass))
        binding.toolbar.ivMenu.isVisible = true
    }


    private val mViewModel: AuthViewModel by viewModels()

    //  private lateinit var parent: MainActivity
    override fun onFragmentReady() {
        onclick()
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
            is AuthAction.ShowFailureMsg -> action.message?.let {
                // if (it.contains("401")) {
                //   activity?.let { it1 -> Extension.logoutAnAuth(it1) }
                //  } else {
                showToast(action.message)
                showProgress(false)
                //   }
            }
            is AuthAction.ChangedPassword -> {
                showToast(action.message)
                PrefsHelper.clear()

                var intent = Intent(activity, AuthActivity::class.java)
                intent.putExtra(Constants.Start, Constants.login)
                startActivity(intent)
                activity?.finish()
            }


            else -> {}
        }
    }


    private fun onclick() {

        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()

        }
        binding.btnSave.setOnClickListener {
            mViewModel.isValidParamsChangePass(
                binding.etPassword.text.toString(),
                binding.etNewPassword.text.toString(),
                binding.etRpeatNewPassword.text.toString()
            )

        }
    }
}