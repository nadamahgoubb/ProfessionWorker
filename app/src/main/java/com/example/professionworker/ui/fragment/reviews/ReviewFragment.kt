package com.example.professionworker.ui.fragment.reviews

import androidx.core.view.isVisible
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentReviewBinding
import com.example.professionworker.ui.activity.MainActivity

class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    private lateinit var parent: MainActivity

    override fun onFragmentReady() {
        setupToolbar()
    }
    private fun setupToolbar() {
        parent = requireActivity() as MainActivity
        binding.toolbar.tvTitle.setText(resources.getString(R.string.provider_review))
        binding.toolbar.ivMenu.isVisible=true
        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}