package com.example.professionworker.ui.fragment.order


import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentOrderInfoBinding
import com.example.professionworker.ui.activity.MainActivity

class OrderInfoFragment : BaseFragment<FragmentOrderInfoBinding>() {

    private lateinit var parent: MainActivity
    override fun onFragmentReady() {
        setupToolbar()
        setupOrderStatus()
    }

    private fun setupOrderStatus() {


        (arguments?.getString("ORDERID"))?.let {
            when (it) {
                "1" -> {
                    binding.cardCompelted.isVisible = true
           binding.cardBottomBorder.isVisible=false
                }

                "2" -> {
                    binding.cardInProgress.isVisible = true
                    binding.cardBottomInProgress.isVisible = true
                }

                "3" -> {
                    binding.cardNewOrder.isVisible = true
                    binding.cardBottomAcceptOrder.isVisible = true
                }

            }
        }
    }

    private fun setupToolbar() {
        parent = requireActivity() as MainActivity
        binding.toolbar.tvTitle.setText(resources.getString(R.string.orders_details))
        binding.toolbar.ivMenu.isVisible = false
        parent.showBottomNav(false)
        parent.showSideNav(false)

        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}