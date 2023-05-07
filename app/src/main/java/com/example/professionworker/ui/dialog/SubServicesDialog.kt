package com.example.professionworker.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.profession.data.dataSource.response.CitesItemsResponse
import com.example.profession.data.dataSource.response.ServicesItemsResponse
import com.example.profession.data.dataSource.response.SubServiceItemsResponse
import com.example.profession.ui.adapter.ServicesHomeAdapter
import com.example.profession.ui.adapter.SubServiceAdapter

import com.example.professionworker.util.ext.init
import com.example.professionworker.R
import com.example.professionworker.databinding.DialogCitiesBinding
import com.example.professionworker.databinding.DialogSubservicesBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.ui.adapter.CitesAdapter
import com.example.professionworker.ui.adapter.CitesListener
import com.example.professionworker.ui.fragment.login.AuthAction
import com.example.professionworker.ui.fragment.login.AuthViewModel
import com.example.professionworker.ui.listener.ServiceOnClickListener
import com.example.professionworker.ui.listener.SubServiceListener
import com.example.professionworker.util.ToastUtils.Companion.showToast
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.ext.showActivity
import com.example.professionworker.util.observe

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SubServicesDialog(
    private val onClick: SubServiceListener,val serviceId:String
 ) : DialogFragment(R.layout.dialog_subservices),   SubServiceListener {
    private val mViewModel: AuthViewModel by viewModels()

    private lateinit var binding:DialogSubservicesBinding
    private lateinit var adapter: SubServiceAdapter
    var choosenSubService: ArrayList<SubServiceItemsResponse>? = arrayListOf()


    companion object {
        fun newInstance(
            onClick: SubServiceListener,   serviceId:String
         ): SubServicesDialog {
            val args = Bundle()
            val f = SubServicesDialog(
                onClick,serviceId  )
            f.arguments = args
            return f
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (dialog?.isShowing == false) {
            binding = DialogSubservicesBinding.inflate(inflater)
            return binding.root
        } else return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        dialog!!.window?.setLayout(width, height)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window?.setGravity(Gravity.CENTER)
        setLayoutHorizintalBottom(binding.root, 100f)


        /*   (arguments?.getParcelableArrayList<CitesItemsResponse>(Constants.CITIES))?.let {
               categories= it
           }*/
        initAdapters()
         mViewModel.apply {
            getSubService(serviceId)
            observe(viewState) {
                handleViewState(it)
            }
        }
        binding.btnDone.setOnClickListener {
            if(choosenSubService.isNullOrEmpty()) showToast(requireContext(), resources.getString(R.string.please_select_subservie))
        else{    onClick.onSubServiceClickListener(choosenSubService!!)
            dialog?.dismiss()
        }}
    }
    private fun handleViewState(action: AuthAction) {
        when (action) {
            is AuthAction.ShowLoading -> {
                showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }


            is AuthAction.ShowFailureMsg -> action.message?.let {
                showToast(requireContext(),action.message)
                showProgress(false)

            }

            is    AuthAction.ShowSubService -> {
                showProgress(false)
                lifecycleScope.launch {
                    adapter.submitData(action.data)
                }
            }
            else -> {

            }
        }
    }

      fun showProgress(show: Boolean) {
          binding.progress.isVisible= show

    }
    fun setLayoutHorizintalBottom(view: View, bottom: Float) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.rightMargin = bottom.toInt()
        layoutParams.leftMargin = bottom.toInt()
        view.layoutParams = layoutParams
    }




    private fun initAdapters() {

        adapter  = SubServiceAdapter(this )

        binding.rvCat.init(requireContext(), adapter, 2)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    binding.preProg.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }
        adapter.addLoadStateListener { loadState ->

            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                binding.lytEmptyState.visibility = View.GONE
                binding.lytData.visibility = View.VISIBLE
            }
            if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                binding.lytData.visibility = View.GONE

                binding.lytEmptyState.visibility = View.VISIBLE
            } else {
                binding.lytEmptyState.visibility = View.GONE
                binding.lytData.visibility = View.VISIBLE
                // If we have an error, show a toast*/
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    /* if (it.error.message.equals(Constants.UNAUTHURAIZED_ACCESS)) {
                         showEmptyState(true)
                     } else*/
                    Toast.makeText(activity, it.error.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }

            }
        }




    }

    override fun onSubServiceClickListener(items: ArrayList<SubServiceItemsResponse>) {
        choosenSubService = items
       }

}
