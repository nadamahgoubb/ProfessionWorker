package com.example.professionworker.ui.fragment.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentSettingBinding
import com.example.professionworker.ui.activity.MainActivity


class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    private lateinit var parent: MainActivity
    override fun onFragmentReady() {
        setupUi()

    }
    private fun setupUi() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(false)
    //    binding.toolBarLayout.
        parent.showSideNav(true)
        binding.toolbar.tvTitle.setText(resources.getString(R.string.settings))
        binding.toolbar.ivMenu.isVisible = true

        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}