package com.dot_jo.professionworker.ui.fragment.contactFragments

import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.base.BaseFragment
 import com.dot_jo.professionworker.databinding.FragmentCustomerServiceBinding
import com.dot_jo.professionworker.ui.activity.MainActivity
import com.dot_jo.professionworker.util.ext.hideKeyboard
import com.dot_jo.professionworker.util.ext.showActivity
import com.dot_jo.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComplainFragment : BaseFragment<FragmentCustomerServiceBinding>() {
    val mViewModel: SupportViewModel by viewModels()
    private lateinit var parent: MainActivity
    override fun onFragmentReady() {
        setupUi()
        mViewModel.apply {
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
            is SupportAction.ShowComplainSucces -> action.message?.let {
                showToast(action.message)
                showProgress(false)
showActivity(MainActivity::class.java, clearAllStack = true)
            }

            else -> {

            }
        }
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
            showActivity( MainActivity::class.java, clearAllStack = true )        }

        binding.btnDone.setOnClickListener {
            mViewModel.validateComplain(binding.etComplainTitle.text.toString(),
                binding.etComplainContent.text.toString())
        }
    }


}