package com.example.professionworker.ui.fragment.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentRegisterBankAccountBinding
import com.example.professionworker.databinding.FragmentRegisterBinding
import com.example.professionworker.ui.activity.MainActivity

class RegisterBankAccountFragment : BaseFragment<FragmentRegisterBankAccountBinding>() {
    override fun onFragmentReady() {
binding.btnRegister.setOnClickListener {
  findNavController().navigate(R.id.subscribationFragment)
}
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }  }



}