package com.example.professionworker.ui.fragment.setting

import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.data.repo.PrefsHelper
import com.example.professionworker.databinding.FragmentSettingBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.util.Constants
import com.example.professionworker.util.ext.showActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    private lateinit var parent: MainActivity
    var current=""
    override fun onFragmentReady() {
        setupUi()
        onBack()
    }
    private fun onBack() {
        activity?.let {
            requireActivity().onBackPressedDispatcher.addCallback(it,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {

                            if (isEnabled) {
                                isEnabled = false
                                showActivity(MainActivity::class.java, clearAllStack = true)
                            }

                    }
                })
        }
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
showActivity( MainActivity::class.java, clearAllStack = true )        }
        current = PrefsHelper.getLanguage()
        if(current.equals(Constants.EN)) binding.btnLang.setText(resources.getString(R.string.arabic))
        else  binding.btnLang.setText(resources.getString(R.string.english))

        binding.btnLang.setOnClickListener {
            if(current.equals(Constants.EN))
                PrefsHelper.setLanguage(Constants.AR)
            else   PrefsHelper.setLanguage(Constants.EN)
            showActivity(MainActivity::class.java, clearAllStack = true)
        }
    }

}