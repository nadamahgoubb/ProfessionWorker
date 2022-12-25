package com.example.professionworker.ui.fragment


import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
 import com.example.professionworker.databinding.FragmentRightAndTermsBinding
import com.example.professionworker.ui.activity.MainActivity

class RightAndTermsFragment : BaseFragment<FragmentRightAndTermsBinding>() {
    private lateinit var parent: MainActivity
    override fun onFragmentReady() {
        setupToolbar()
    }

    private fun setupToolbar() {
        parent = requireActivity() as MainActivity
        binding.toolbar.tvTitle.setText(resources.getString(R.string.rights_and_terms))
        binding.toolbar.ivMenu.isVisible = true
        parent.showBottomNav(false)
        parent.showSideNav(true)

        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}