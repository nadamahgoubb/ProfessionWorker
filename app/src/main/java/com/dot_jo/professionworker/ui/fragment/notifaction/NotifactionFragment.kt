package com.dot_jo.professionworker.ui.fragment.notifaction


import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.base.BaseFragment
import com.dot_jo.professionworker.data.response.NotificationsItem
import com.dot_jo.professionworker.databinding.FragmentNotifactionBinding
import com.dot_jo.professionworker.ui.activity.MainActivity
import com.dot_jo.professionworker.ui.adapter.NotifactionAdapter
import com.dot_jo.professionworker.ui.fragments.order.NotifactionAction
import com.dot_jo.professionworker.util.ext.hideKeyboard
import com.dot_jo.professionworker.util.ext.init
import com.dot_jo.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class NotifactionFragment : BaseFragment<FragmentNotifactionBinding>() {
    private val mViewModel: NotifactionViewModel by viewModels()
    lateinit var adapter: NotifactionAdapter
    private lateinit var parent: MainActivity

    override fun onFragmentReady() {
        setupToolbar()
        mViewModel.apply {
            getNotifaction()
            observe(viewState) {
                handleViewState(it)
            }
        }
        binding.swiperefresh.setOnRefreshListener {
            mViewModel.getNotifaction()
            if (binding.swiperefresh != null) binding.swiperefresh.isRefreshing = false
        }  }

    private fun handleViewState(action: NotifactionAction) {
        when (action) {
            is NotifactionAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }

            is NotifactionAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }

            is NotifactionAction.ShowNotifaction -> {
                showProgress(false)
                action.data.notifications?.let { loadNotifaction(it) }
            }

            else -> {}
        }
    }

    private fun loadNotifaction(notifications: ArrayList<NotificationsItem>) {
        if (notifications?.size!! > 0) {
            binding.lytEmptyState.isVisible = false
            adapter.list = notifications
            adapter.notifyDataSetChanged()  }
        else {
            binding.lytEmptyState.isVisible = true
            adapter.list = arrayListOf()
            adapter.notifyDataSetChanged()
        }


    }
    private fun setupToolbar() {
        parent = requireActivity() as MainActivity
        parent.showBottomNav(true)
        parent.showSideNav(true)
        binding.ivMenu.setOnClickListener {
            parent.openDrawer()
        }
        adapter=NotifactionAdapter( )
        binding.rvOffersHome.init(requireContext(),adapter,2)


    }

}