package com.example.professionworker.ui.fragment.register


import android.content.Intent
import android.graphics.Paint
import androidx.activity.OnBackPressedCallback
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentRegisterBinding
 import com.example.professionworker.ui.dialog.DeleteAccountSheetFragment
 import com.example.professionworker.ui.dialog.OnProviderClick
import com.example.professionworker.ui.dialog.ProviderTypeSheetFragment

import com.example.professionworker.ui.fragment.login.AuthViewModel
import com.example.professionworker.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    private val mViewModel: AuthViewModel by activityViewModels()
    var state = 1
  var  providerType :String? =null
    override fun onFragmentReady() {
        onClick()
        onBack()

    }

    private fun onBack() {
        activity?.let {
            requireActivity().onBackPressedDispatcher.addCallback(it,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        when (state) {
                            1 -> {

                                if (isEnabled) {
                                    isEnabled = false
                                    requireActivity().onBackPressed()
                                }
                            }
                            2 -> stateOne()


                        }
                    }
                })


        }
    }

    private fun onClick() {
        binding.terms.text =
            HtmlCompat.fromHtml(getString(R.string.some_text), HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.btnRegister.setOnClickListener {
            if (state == 1) {

                if (!providerType.isNullOrBlank()) showProviderTypeSheetFragment()
                else stateSec()
            }
            else {
                isVaildRegisterationData()
                findNavController().navigate(R.id.registerBankAccountFragment)
            }



            }

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)

        }
        binding.lytHeader.ivBack.setOnClickListener {
            if (state == 2)  stateOne()
            else {

                activity?.onBackPressed()
            }
        }
        binding.btnSignIn.setPaintFlags(binding.btnSignIn.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

    private fun isVaildRegisterationData() {
      //  mViewModel.validateRegisteration()
    }

    private fun stateSec() {
        state = 2
        binding.lytData1.isVisible = false
        binding.lytData2.isVisible = true

    }

    private fun stateOne() {
        state = 1
        binding.lytData1.isVisible = true
        binding.lytData2.isVisible = false
    }

    private fun showProviderTypeSheetFragment() {
        ProviderTypeSheetFragment.newInstance(object : OnProviderClick {
            override fun onClick(choice: String) {
                if (choice.equals(Constants.PERSON)) {
                providerType= Constants.PERSON
                    stateSec()

                } else if(choice.equals(Constants.COMPANY)) {
                        providerType= Constants.PERSON
                    stateSec()
                }
            }


        }).show(childFragmentManager, DeleteAccountSheetFragment::class.java.canonicalName)
    }
}