package com.example.professionworker.ui.fragment.contactFragments

import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentRightAndTermsBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.ext.showActivity
import com.example.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RightAndTermsFragment : BaseFragment<FragmentRightAndTermsBinding>() {
    private lateinit var parent: MainActivity

    val mViewModel: SupportViewModel by viewModels()

    override fun onFragmentReady() {
        setupToolbar()


        mViewModel.apply {
            getTerms()
            observe(viewState) {
                handleViewState(it)
            }
        }
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

    private fun handleViewState(action: SupportAction) {
        when (action) {
            is SupportAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }

            is SupportAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }
            is SupportAction.ShowTerms -> {

                binding.tvTerms.setText(action.data?.content.toString())

            }


            else -> {

            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.tvTitle.setText(resources.getString(R.string.rights_and_terms))

        binding.toolbar.ivBack.setOnClickListener {

                showActivity(MainActivity::class.java, clearAllStack = true)

        }


        try {

            parent = requireActivity() as MainActivity
            binding.toolbar.ivMenu.isVisible = true
            binding.toolbar.ivMenu.setOnClickListener {
                parent.openDrawer()
            }
            parent.showBottomNav(false)
            parent.showSideNav(true)


        } catch (e: Exception) {
             binding.toolbar.ivMenu.isVisible = false
        }
    }
}