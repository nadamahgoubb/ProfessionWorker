package com.example.professionworker.ui.fragment.register


import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentRegisterBankAccountBinding
import com.example.professionworker.ui.fragment.login.AuthViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RegisterBankAccountFragment : BaseFragment<FragmentRegisterBankAccountBinding>() {
    private val mViewModel: AuthViewModel by activityViewModels()
    override fun onFragmentReady() {
binding.btnRegister.setOnClickListener {
  findNavController().navigate(R.id.subscribationFragment)
}
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }  }



}