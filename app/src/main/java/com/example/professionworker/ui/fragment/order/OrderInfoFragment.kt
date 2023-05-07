package com.example.professionworker.ui.fragment.order


import android.content.Intent
import android.net.Uri
import android.widget.RatingBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.profession.ui.adapter.OrdersAdapter
import com.example.professionworker.R
import com.example.professionworker.base.BaseFragment
import com.example.professionworker.data.params.CancelOrderParam
import com.example.professionworker.data.params.OrderActionsParams
import com.example.professionworker.domain.response.OrdersItem
import com.example.professionworker.databinding.FragmentOrderInfoBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.ui.adapter.CheckoutSubserviceAdapter
import com.example.professionworker.ui.fragments.order.OrdersAction
import com.example.professionworker.util.Constants
import com.example.professionworker.util.Utils.getPaymentMethod
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.ext.init
import com.example.professionworker.util.ext.roundTo
import com.example.professionworker.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderInfoFragment : BaseFragment<FragmentOrderInfoBinding>() {
    private lateinit var parent: MainActivity
    private val mViewModel: OrdersViewModel by activityViewModels()
    lateinit var adapter: OrdersAdapter

    lateinit var adapter_subservice: CheckoutSubserviceAdapter
    var status = "-1"
    override fun onFragmentReady() {

         setupToolbar()
        onClick()
        mViewModel.apply {
            getOrderDetails(mViewModel.orderId)
            observe(viewState) {
                handleViewState(it)
            }
        }
        (arguments?.getString("ORDERID"))?.let {
            status = it

            when (it) {
                Constants.New_ORDER -> {
                    binding.cardWaitingApproval.isVisible = true
                     binding.cardCancelOrder.isVisible = true
                }
                Constants.CURRENT_ORDER -> {
                    binding.cardInProgress.isVisible = true
                    binding.cardCompeletOrder.isVisible = true
                }
                Constants.PREV_ORDER -> {
                     binding.cardCompelted.isVisible = true
                    binding.cardCompeletOrder.isVisible = true
                    binding.cardPersonalInfo.isVisible = false
                }


            }
        }
        binding.swiperefreshHome.setOnRefreshListener {
            mViewModel.getOrderDetails(mViewModel.orderId)
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
            is OrdersAction.ShowOrderDetails -> {
                showProgress(false)
                showData(action.data)
            }

            is OrdersAction.ShowFailureMsg -> action.message?.let {
                showToast(action.message)
                showProgress(false)

            }

            is OrdersAction.ShowCanceledOrder ->{
                showToast(action.message)
                showProgress(false)
                binding.cardPrice.isVisible = false
            }


            else -> {

            }
        }
    }

    lateinit var data: OrdersItem
    var orderId: String? = null
    private fun showData(data: OrdersItem) {
        binding.lytData.isVisible = true
        this.data = data
        binding.tvOrderId.setText(data.orderId)
        this.orderId = data.orderId
        data.paymentMethod?.let {
            var paymentData = getPaymentMethod(it, requireContext())
            binding.tvCash.setText(paymentData?.title)
            binding.ivLogoPayment.setImageDrawable(
                resources.getDrawable(paymentData?.logo!!)
            )
        }

        binding.tvName.setText(data?.userName)
         binding.tvAddress.setText(data?.address)
        binding.ivCall.setOnClickListener {
            data.userPhone?.let { it1 -> call(it1) }
        }
        binding.tvTime.setText(data.orderTime)
        binding.tvDate.setText(data.orderDate)
       // binding.tvPrice.setText(data.providerHourPrice.toString())
        binding.tvTimeinService.setText(data.countHours.toString() + resources.getText(R.string.hour))
        binding.tvTotalBeforetax.setText(data.total?.toString())
        binding.tvTax.setText(data.tax?.toDoubleOrNull()?.roundTo(2).toString())
        binding.tvTotalPrice.setText(data.finalTotal)

        adapter_subservice.itemsList = data.subServices
        adapter_subservice.notifyDataSetChanged()


    }

    fun call(tel: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + tel)
        startActivity(dialIntent)
    }

    var rate = 0F
    private fun onClick() {


        binding.btnReject.setOnClickListener {
            orderId?.let { it1 -> mViewModel.showOrderAction(OrderActionsParams(it1,data.orderStatus.toString(), Constants.CANCEL)) }
        }
        binding.btnCompelete.setOnClickListener {
            orderId?.let { it1 -> mViewModel.showOrderAction(OrderActionsParams(it1,data.orderStatus.toString(), Constants.COMPELET)) }
        }
  binding.btnAccept.setOnClickListener {
            orderId?.let { it1 -> mViewModel.showOrderAction(OrderActionsParams(it1,
                data.orderStatus.toString(), Constants.ACCEPT)) }
        }


    }

    private fun setupToolbar() {
        parent = requireActivity() as MainActivity

        binding.toolbar.tvTitle.setText(resources.getString(R.string.orders_details))
        binding.toolbar.ivMenu.isVisible = false
        parent.showBottomNav(false)
        parent.showSideNav(false)
        var servicetitle = ""
        servicetitle?.let {
            adapter_subservice = CheckoutSubserviceAdapter(it)
            binding.rvSubservice.init(context, adapter_subservice, 2)
        }

        binding.toolbar.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}