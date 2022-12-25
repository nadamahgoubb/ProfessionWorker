package com.example.professionworker.ui.fragment.contactFragments

import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
 import com.example.professionworker.databinding.FragmentCustomerServiceBinding
import com.example.professionworker.ui.activity.MainActivity

class CustomerServiceFragment : BaseFragment<FragmentCustomerServiceBinding>() {
    private lateinit var parent: MainActivity
    override fun onFragmentReady() {
        setupUi()
    }

    private fun setupUi() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(false)
        parent.showSideNav(true)
        binding.toolbar.tvTitle.setText(resources.getString(R.string.complains))
        binding.toolbar.ivMenu.isVisible = true
        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }


}