package com.example.professionworker.ui.fragment.forgertPass


import android.graphics.Paint
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.databinding.FragmentForgetPasswordBinding
import com.example.professionworker.base.BaseFragment

class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {
   var state= 1
    override fun onFragmentReady() {
    //    showProgress(true)
        state1()
        binding.btnResend.setPaintFlags(binding.btnResend.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        binding.btnNext.setOnClickListener {
            state2()
        }
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        binding.btnResend.setOnClickListener {
            //    state2()
        }
        binding.btnSendOtp.setOnClickListener {
            state3()
        }
        binding.ivBack.setOnClickListener {

            when(state){
                1-> {


                        activity?.let {
                            findNavController().navigate(R.id.action_forgetPasswordFragment_to_loginFragment)
                        }

                }
                2-> state1()
                3->state2()

            }
        }

        onBack()

    }

    private fun onBack() {
        activity?.let {
            requireActivity().onBackPressedDispatcher.addCallback(
                it,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        when(state){
                            1-> {

                                if (isEnabled) {
                                    isEnabled = false
                                    requireActivity().onBackPressed()
                                }
                            }
                            2-> state1()
                            3->state2()

                        }
                    }
                })


        }    }

    private fun state2() {
    state= 2
      binding.lytFirst.isVisible=false
        binding.lytChangePass.isVisible=false
        binding.lytOtp.isVisible = true
        binding.tvcounter.isVisible= true
}

    private fun state1() {
      state=1
        binding.lytFirst.isVisible=true
        binding.tvcounter.isVisible= false
        binding.lytOtp.isVisible = false
    binding.lytChangePass.isVisible=false}

    private fun state3() {
    state=3
        binding.tvcounter.isVisible= false
        binding.lytFirst.isVisible=false
        binding.lytOtp.isVisible = false
        binding.lytChangePass.isVisible=true    }
}