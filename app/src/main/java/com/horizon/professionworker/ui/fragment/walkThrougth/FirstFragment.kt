package com.horizon.professionworker.ui.fragments.walkThrough

import com.horizon.professionworker.R
import com.horizon.professionworker.databinding.ItemWalkthrougthBinding
import com.horizon.professionworker.base.BaseFragment
import com.horizon.professionworker.util.ext.loadImage


interface OnClickSkipListener {
    fun onSkipClickListener()
}
class FirstFragment( val state: Int, val  listener: OnClickSkipListener) : BaseFragment<ItemWalkthrougthBinding>() {


    private fun setUpUi() {
  when (state) {
            0 -> {
                binding.tvTitle.text = resources.getString(R.string.services)
                binding.tvMsg.text =
                    getString(R.string.offer_your_service)
                binding.imgSlider.loadImage(R.drawable.walkthrought_0)


            }
            1 -> {
                binding.tvTitle.text = getString(R.string.with_simple_steps)
                binding.tvMsg.text =
                    getString(R.string.offer_your_service_with_price)
                binding.imgSlider.loadImage(R.drawable.walkthrougth_1)


            }


        }

    }

    override fun onFragmentReady() {
        setUpUi()
        binding.tvSkip.setOnClickListener {
            listener.onSkipClickListener()
        }
    }
}