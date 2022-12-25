package com.example.professionworker.ui.fragment.changePass

import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentChangePassBinding


class ChangePassFragment : BaseFragment<FragmentChangePassBinding>() {
    private fun setupToolbar() {
        binding.toolbar.tvTitle.setText(resources.getString(R.string.provider_review))
        binding.toolbar.ivMenu.isVisible = true
    }

    override fun onFragmentReady() {
        setupToolbar()
    }
}