package com.example.professionworker.ui.fragment.register


import android.content.Intent
import android.graphics.Paint
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentRegisterBinding
import com.example.professionworker.databinding.FragmentSplashBinding
import com.example.professionworker.ui.activity.MainActivity

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun onFragmentReady() {
        binding.terms.text =
            HtmlCompat.fromHtml(getString(R.string.some_text), HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerBankAccountFragment)

        }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)

        }
        binding.btnSignIn.setPaintFlags( binding.btnSignIn.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

}