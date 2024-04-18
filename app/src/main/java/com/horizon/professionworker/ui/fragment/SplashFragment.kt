package com.horizon.professionworker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.horizon.professionworker.R
import com.horizon.professionworker.base.BaseFragment
import com.horizon.professionworker.data.repo.PrefsHelper
import com.horizon.professionworker.databinding.FragmentSplashBinding
import com.horizon.professionworker.ui.activity.MainActivity
import com.horizon.professionworker.util.ext.showActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun onFragmentReady() {
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            delay(800)
            fadeAnimation()
            if (PrefsHelper.getIsloggedInBefore()) {
                if (PrefsHelper.getToken()
                        .isNullOrBlank()
                ) findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                else showActivity(MainActivity::class.java, clearAllStack = true)


            } else {
                PrefsHelper.setloggedInBefore(true)
                findNavController().navigate(R.id.action_splashFragment_to_walkThrougthFragment)
            }
        }
    }

    private fun fadeAnimation() {
        val fadeOut: Animation = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.startOffset = 1000
        fadeOut.duration = 800
        val animation = AnimationSet(false)
        animation.addAnimation(fadeOut)
        animation.addAnimation(fadeOut)
        binding.root.animation = animation
    }


}