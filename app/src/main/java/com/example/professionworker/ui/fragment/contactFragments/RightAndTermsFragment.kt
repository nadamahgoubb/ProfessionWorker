package com.example.professionworker.ui.fragment.contactFragments

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
 import com.example.professionworker.databinding.FragmentRightAndTermsBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.util.ext.hideKeyboard
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
            }   }
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
                is SupportAction.ShowTerms ->  {

                    binding.tvTerms.setText(action.data?.content.toString())

                }


                else -> {

                }
            }
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