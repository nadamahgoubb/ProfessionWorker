package com.example.professionworker.ui.fragment.login

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentLoginBinding
import com.example.professionworker.ui.activity.MainActivity

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun onFragmentReady() {
        binding.tvForgetPass.setPaintFlags( binding.tvForgetPass.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.tvCreate.setPaintFlags( binding.tvCreate.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        }
        binding.tvCreate.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        binding.tvForgetPass.setOnClickListener {
            findNavController().navigate(R.id.forgetPasswordFragment)
        }

}

}