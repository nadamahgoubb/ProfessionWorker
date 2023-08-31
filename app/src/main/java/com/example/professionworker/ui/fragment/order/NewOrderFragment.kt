package com.example.professionworker.ui.fragment.order

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
 import androidx.navigation.fragment.findNavController
import com.example.profession.ui.adapter.OrdersAdapter
import com.example.profession.ui.adapter.OrdersClickListener
import com.example.professionworker.R

import com.example.professionworker.base.BaseFragment
import com.example.professionworker.data.repo.PrefsHelper
import com.example.professionworker.domain.response.OrdersItem
import com.example.professionworker.databinding.FragmentOrderItemBinding
import com.example.professionworker.ui.activity.AuthActivity
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.ui.fragments.order.OrdersAction
import com.example.professionworker.util.Constants
import com.example.professionworker.util.ext.chat
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.ext.init
import com.example.professionworker.util.ext.showActivity
import com.example.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class NewOrderFragment( ) : BaseFragment<FragmentOrderItemBinding>(),
    OrdersClickListener {
    private val mViewModel: OrdersViewModel by activityViewModels()
    lateinit var adapter: OrdersAdapter
    override fun onFragmentReady() {

        initAdapter()
         mViewModel.apply {
           getNewOrders( Constants.New_ORDER)
            observe(viewState) {
                handleViewState(it)
            }
        }
        binding.swiperefreshHome.setOnRefreshListener {
            mViewModel.getNewOrders(Constants.New_ORDER)
            if (binding.swiperefreshHome != null) binding.swiperefreshHome.isRefreshing = false
        }
    }

    private fun handleViewState(action: OrdersAction) {
        when (action) {
            is OrdersAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }
            is OrdersAction.ShowOrders -> {
                showProgress(false)
                if (action.state == Constants.New_ORDER) {
                    if (action.data.orders.isNullOrEmpty() == false) {
                        binding.lytEmptyState.isVisible = false
                        adapter.list = action.data.orders
                        adapter.notifyDataSetChanged()
                    } else {
                        binding.lytEmptyState.isVisible = true
                    }
                }
            }
                is OrdersAction.ShowFailureMsg ->
                {
                    if (action.message?.contains("401") == true) {
                        PrefsHelper.clear()
                        var intent = Intent(requireContext(), AuthActivity::class.java)
                        intent.putExtra(Constants.Start, Constants.login)
                        startActivity(intent)
                        requireActivity()?.finish()
                    } else {
                        action.message?.let {
                            showToast(action.message)
                            showProgress(false)

                        }
                    }
                }


            else -> {

            }
        }
    }

    var bundle = Bundle()

    private fun initAdapter() {
        adapter = OrdersAdapter( Constants.New_ORDER  , this)
        binding.rvOrders.init(requireContext(), adapter, 2)
        adapter.list.clear()
        adapter.notifyDataSetChanged()
    }

    override fun onOrderDetailsClicked(item: OrdersItem?) {
        bundle.putString(Constants.ORDERID,  Constants.New_ORDER)
        mViewModel.orderId= item?.orderId.toString()
        mViewModel.data= item
        findNavController().navigate(R.id.orderInfoFragment, bundle)
    }

    override fun onOrderCallClicked(item: OrdersItem?) {
        item?.userPhone?.let { call(it) }

    }

    override fun onOrderChatClicked(item: OrdersItem?) {
        item?.userPhone?.let {
            item.countryCode?.let { it1 ->
                chat(
                    requireContext(),
                    it1,
                    it
                )
            }
        }
    }

    fun call(tel: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + tel)
        startActivity(dialIntent)
    }
}