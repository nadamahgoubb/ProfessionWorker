package com.example.professionworker.ui.fragments.walkThrough

import android.content.Intent
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.professionworker.R
import com.example.professionworker.databinding.FragmentWalkThrougthBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WalkThrougthFragment() : BaseFragment<FragmentWalkThrougthBinding>(), OnClickSkipListener {

    private var pos = 0
    override fun onFragmentReady() {
        setupViewPager()
        onClick()

    }


    private fun setupViewPager() {
    binding.btnNext.setText(R.string.app_name)
        val adapter = SectionsPagerAdapter(this,this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ ->
            binding.viewPager.currentItem = 0
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

                super.onPageSelected(position)
                pos = position

                when (pos) {
                    0 -> {
                        binding.btnNext.setText(R.string.next)


                    }
                    1 -> {
                        binding.btnNext.setText(R.string.start)

                    }

                }
            }
        })
    }

    private fun onClick() {
       binding.btnNext.setOnClickListener {
            if (pos == 0 ) {
                pos++
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            }
           else{
             findNavController().navigate(R.id.action_walkThrougthFragment_to_loginFragment)
             }

        }


    }


    private fun goNext() {
        if (pos == 0 ) {
            pos++
            binding.viewPager.currentItem = binding.viewPager.currentItem + 1
        }
    }

    override fun onSkipClickListener() {
        findNavController().navigate(R.id.action_walkThrougthFragment_to_loginFragment)
    }


}