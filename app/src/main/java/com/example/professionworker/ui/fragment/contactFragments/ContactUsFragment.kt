package com.example.professionworker.ui.fragment.contactFragments

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.databinding.FragmentContactUsBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.ui.fragment.login.AuthAction
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.ext.showActivity
import com.example.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactUsFragment : BaseFragment<FragmentContactUsBinding>() {

    private lateinit var parent: MainActivity
    val mViewModel: SupportViewModel by viewModels()

    override fun onFragmentReady() {

        setupUi()
        onClick()
        mViewModel.apply {
            getGoals()
            observe(viewState) {
                handleViewState(it)
            }
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
            is SupportAction.ShowContactSucces -> action.message?.let {
                showToast(action.message)
                showProgress(false)
                activity?.onBackPressed()

            }
            is SupportAction.ShowGoals -> binding.tvGoal.setText(action.data?.content.toString())


            else -> {

            }
        }
    }

    private fun onClick() {

        binding.btnDone.setOnClickListener {
            if (binding.etMsg.text.toString()
                    .isEmpty()
            ) showToast(resources.getString(R.string.msg_empty_content))
            else mViewModel.contact(binding.etMsg.text.toString())
        }
    }


    private fun setupUi() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(false)
        parent.showSideNav(true)
        binding.toolbar.tvTitle.setText(resources.getString(R.string.contact_with_app))
        binding.toolbar.ivMenu.isVisible = true
        binding.toolbar.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        binding.toolbar.ivBack.setOnClickListener {
            //  showActivity(MainActivity::class.java, clearAllStack = true)

            activity?.onBackPressed()
        }
    }
}