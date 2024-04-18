package com.horizon.professionworker.ui.fragment.order

import android.content.Intent
import android.net.Uri
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.horizon.profession.ui.adapter.OrdersAdapter
import com.horizon.professionworker.R
import com.horizon.professionworker.base.BaseFragment
import com.horizon.professionworker.data.params.OrderActionsParams
import com.horizon.professionworker.data.repo.PrefsHelper
import com.horizon.professionworker.data.response.OrdersItem
import com.horizon.professionworker.databinding.FragmentOrderInfoBinding
import com.horizon.professionworker.ui.activity.AuthActivity
import com.horizon.professionworker.ui.activity.MainActivity
import com.horizon.professionworker.ui.adapter.CheckoutSubserviceAdapter
import com.horizon.professionworker.ui.fragments.order.OrdersAction
import com.horizon.professionworker.util.Constants
import com.horizon.professionworker.util.Utils
import com.horizon.professionworker.util.Utils.getPaymentMethod
import com.horizon.professionworker.util.ext.*
import com.horizon.professionworker.util.observe
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
            observe(viewState) {
                handleViewState(it)
            }
        }
        binding.swiperefreshHome.setOnRefreshListener {
            mViewModel.getOrderDetails(mViewModel.orderId)
            if (binding.swiperefreshHome != null) binding.swiperefreshHome.isRefreshing = false
        }
    }

    fun handleState(status: String) {
        when (status) {
            Constants.New_ORDER -> {
                binding.cardNewOrder.isVisible = true
                binding.cardCancelOrder.isVisible = true
                binding.cardCompeletOrder.isVisible = false
            }

            Constants.CURRENT_ORDER -> {
                binding.cardNewOrder.isVisible = false
                binding.cardCancelOrder.isVisible = false

                binding.cardInProgress.isVisible = true
                binding.cardCompeletOrder.isVisible = true
            }

            Constants.PREV_ORDER -> {
                binding.cardInProgress.isVisible = false
                binding.cardCompeletOrder.isVisible = false
                binding.cardPrice.isVisible = false
                binding.cardCompelted.isVisible = true
                 binding.cardPersonalInfo.isVisible = false
            }


        }


    }

    override fun onResume() {
        super.onResume()
        mViewModel.            getOrderDetails(mViewModel.orderId)

    }

    private fun handleViewState(action: OrdersAction) {
        when (action) {
            is OrdersAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }


            is OrdersAction.ShowFailureMsg -> {
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

            is OrdersAction.ShowOrderActions -> {
                showToast(action.message)
                binding.cardPrice.isVisible = false

                if (action.params.action_type.equals(Constants.CANCEL) || action.params.action_type.equals(Constants.COMPELET)) activity?.onBackPressed()
                else mViewModel.getOrderDetails(mViewModel.orderId)
            }

            is OrdersAction.ShowOrderDetails -> {
                showData(action.data)
            }


            else -> {

            }
        }
    }


    lateinit var data: OrdersItem
    var orderId: String? = null
    private fun showData(data: OrdersItem) {
        data?.let {
            this.data = it
            binding.lytData.isVisible = true
            binding.tvOrderId.setText(this.data.orderId)
            data.orderStatus?.let {
                status = it
                handleState(it)
            }
            this.orderId = this.data.orderId
            this.data.paymentMethod?.let {
                var paymentData = getPaymentMethod(it, requireContext())
                binding.ivLogoPayment.setImageDrawable(
                    resources.getDrawable(paymentData?.logo!!)
                )
                if (it == Constants.VISA) {
                    if (this.data.confirm_payment_visa == 0 && this.data.finalTotal != null) {
                        binding.tvCash.setText(paymentData?.title + resources.getString(R.string.not_paid_yet))
                    } else {
                        if (this.data.confirm_payment_visa != null) binding.tvCash.setText(
                            paymentData?.title + resources.getString(R.string.paid)
                        )
                        else binding.tvCash.setText(paymentData?.title)

                    }
                } else {
                    binding.tvCash.setText(paymentData?.title)


                }
            }

            binding.tvName.setText(this.data?.userName)
            binding.ivUser.loadImage(
                this.data?.userPhoto, isCircular = true, placeHolderImage = R.drawable.empty_user
            )
            binding.tvAddress.setText(this.data?.address)
            binding.lytCall.setOnClickListener {
                this.data.userPhone?.let { it -> call(it) }
            }
            binding.lytLocation.setOnClickListener {
                this.data.lat?.let { it -> openMap(requireContext(), it, this.data.lon.toString()) }
            }
            binding.tvTime.setText(   Utils.toTwelevePattern(this.data.orderTime))
            binding.tvDate.setText(this.data.orderDate)
            // binding.tvPrice.setText(data.providerHourPrice.toString())
            binding.tvTimeinService.setText(this.data.countHours.toString() + resources.getText(R.string.hour))
            binding.tvTotalBeforetax.setText(this.data.total?.toString()+resources.getString(R.string.sr))
            binding.tvTax.setText(this.data.tax?.toDoubleOrNull()?.roundTo(2).toString()+resources.getString(R.string.sr))
            binding.tvTotalPrice.setText(this.data.finalTotal?.toDoubleOrNull()?.roundTo(2)?.toString()+resources.getString(R.string.sr))

            adapter_subservice.itemsList = this.data.subServices
            adapter_subservice.notifyDataSetChanged()

        }


    }

    fun call(tel: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + tel)
        startActivity(dialIntent)
    }

    var rate = 0F
    private fun onClick() {


        binding.btnReject.setOnClickListener {
            orderId?.let { it1 ->
                mViewModel.showOrderAction(
                    OrderActionsParams(
                        it1, "underway", Constants.CANCEL
                    )
                )
            }
        }

        binding.btnCompelete.setOnClickListener {
            orderId?.let { it1 ->
                mViewModel.showOrderAction(
                    OrderActionsParams(
                        it1, "previous", Constants.COMPELET
                    )
                )
            }
        }
        binding.btnAccept.setOnClickListener {
            orderId?.let { it1 ->
                mViewModel.showOrderAction(
                    OrderActionsParams(
                        it1, "underway", Constants.ACCEPT
                    )
                )
            }
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