package com.example.professionworker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.data.repo.PrefsHelper
import com.example.professionworker.databinding.FragmentSplashBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.util.ext.showActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun onFragmentReady() {
        lifecycleScope.launch {
            delay(1500)

            if (PrefsHelper.getIsloggedInBefore()) {
                if(PrefsHelper.getToken().isNullOrBlank())                  findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

            else    showActivity(MainActivity::class.java, clearAllStack = true)


            } else {
                 PrefsHelper.setloggedInBefore(true)
                findNavController().navigate(R.id.action_splashFragment_to_walkThrougthFragment)
            }
        }
    }
}