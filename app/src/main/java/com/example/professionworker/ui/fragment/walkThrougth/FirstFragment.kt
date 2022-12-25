package com.example.professionworker.ui.fragments.walkThrough

import com.example.professionworker.R
import com.example.professionworker.databinding.ItemWalkthrougthBinding
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.util.ext.loadImage


class FirstFragment( var state: Int) : BaseFragment<ItemWalkthrougthBinding>() {


    private fun setUpUi() {
  when (state) {
            0 -> {
                binding.tvTitle.text = resources.getString(R.string.services)
                binding.tvMsg.text =
                    getString(R.string.alot_of_workers_are_available)
                binding.imgSlider.loadImage(R.drawable.walkthrought_0)


            }
            1 -> {
                binding.tvTitle.text = getString(R.string.with_simple_steps)
                binding.tvMsg.text =
                    getString(R.string.you_can_have_aprovider)
                binding.imgSlider.loadImage(R.drawable.walkthrougth_1)


            }


        }

    }

    override fun onFragmentReady() {
        setUpUi()
    }
}