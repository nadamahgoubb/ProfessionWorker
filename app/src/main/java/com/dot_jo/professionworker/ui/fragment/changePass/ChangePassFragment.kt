package com.dot_jo.professionworker.ui.fragment.changePass

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.base.BaseFragment
import com.dot_jo.professionworker.data.repo.PrefsHelper
import com.dot_jo.professionworker.databinding.FragmentChangePassBinding
import com.dot_jo.professionworker.ui.activity.AuthActivity
import com.dot_jo.professionworker.ui.activity.MainActivity
import com.dot_jo.professionworker.ui.fragment.login.AuthAction
import com.dot_jo.professionworker.ui.fragment.login.AuthViewModel
import com.dot_jo.professionworker.util.Constants
import com.dot_jo.professionworker.util.ext.hideKeyboard
import com.dot_jo.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ChangePassFragment : BaseFragment<FragmentChangePassBinding>() {
    private lateinit var parent: MainActivity
    private fun setupToolbar() {
        parent = requireActivity() as MainActivity
        binding.toolbar.tvTitle.setText(resources.getString(R.string.change_pass))
        binding.toolbar.ivMenu.isVisible = false
        parent.showBottomNav(false)
        parent.showSideNav(false)

    }


    private val mViewModel: AuthViewModel by viewModels()

    override fun onFragmentReady() {
        setupToolbar()
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