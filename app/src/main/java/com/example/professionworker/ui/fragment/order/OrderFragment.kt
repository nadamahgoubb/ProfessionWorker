package com.example.professionworker.ui.fragment.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentOrderBinding
import com.example.professionworker.ui.activity.MainActivity

class OrderFragment : BaseFragment<FragmentOrderBinding>() {
    private lateinit var parent: MainActivity
    override fun onFragmentReady() {

        setupUi()
        binding.item1.tvStatusCompelted.isVisible = true
        binding.item2.tvStatusMsgWaitingApproval.isVisible = true
        binding.item3.tvStatusCompelted.isVisible = true
        binding.item4.tvStatusMsgWaitingApproval.isVisible = true
        binding.item5.tvStatusNewOrder.isVisible = true
        var bundle = Bundle()
        binding.item1.root.setOnClickListener {
            bundle.putString("ORDERID", "1")
            findNavController().navigate(R.id.orderInfoFragment,bundle)
        }
        binding.item2.root.setOnClickListener {
            bundle.putString("ORDERID", "2")
            findNavController().navigate(R.id.orderInfoFragment,bundle)
        }
        binding.item3.root.setOnClickListener {
            bundle.putString("ORDERID", "1")
            findNavController().navigate(R.id.orderInfoFragment,bundle)
        }
        binding.item4.root.setOnClickListener {
            bundle.putString("ORDERID", "2")
            findNavController().navigate(R.id.orderInfoFragment,bundle)
        }
        binding.item5.root.setOnClickListener {
            bundle.putString("ORDERID", "3")
            findNavController().navigate(R.id.orderInfoFragment,bundle)
        }
    }
    private fun setupUi() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(true)
        parent.showSideNav(true)
        binding.toolbar.tvTitle.setText(resources.getString(R.string.orders))
        binding.toolbar.ivMenu.isVisible=true
        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
