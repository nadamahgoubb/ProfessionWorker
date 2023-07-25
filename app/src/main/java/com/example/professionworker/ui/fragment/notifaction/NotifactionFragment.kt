package com.example.professionworker.ui.fragment.notifaction


import android.view.View
import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentNotifactionBinding
import com.example.professionworker.ui.activity.MainActivity

class NotifactionFragment : BaseFragment<FragmentNotifactionBinding>() {
    private lateinit var parent: MainActivity
    private fun setupToolbar() {
        parent = requireActivity() as MainActivity
        binding.toolbar.tvTitle.setText(resources.getString(R.string.notifactions))
        binding.toolbar.ivMenu. visibility=  View.VISIBLE
        binding.toolbar.ivBack. visibility=  View.INVISIBLE

        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onFragmentReady() {
        setupToolbar()
    }


}