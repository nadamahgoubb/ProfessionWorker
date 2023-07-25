package com.example.professionworker.ui.bottomShet

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.professionworker.R
import com.example.professionworker.databinding.FragmentRightAndTermsBinding
import com.example.professionworker.ui.fragment.contactFragments.SupportAction
import com.example.professionworker.ui.fragment.contactFragments.SupportViewModel
import com.example.professionworker.util.ToastUtils.Companion.showToast
import com.example.professionworker.util.ext.hideKeyboard
import com.example.professionworker.util.observe
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RightAndTermsBottomSheet( ) :
    DialogFragment() {

    private lateinit var binding: FragmentRightAndTermsBinding
    val mViewModel: SupportViewModel by viewModels()

    companion object {
        fun newInstance( ): RightAndTermsBottomSheet {
            val args = Bundle()
            val f = RightAndTermsBottomSheet( )
            f.arguments = args
            return f
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRightAndTermsBinding.inflate(inflater)
         onClick()
        mViewModel.apply {
            getTerms()
            observe(viewState) {
                handleViewState(it)
            }
        }
        return binding.root
    }
    private fun handleViewState(action: SupportAction) {
        when (action) {
            is SupportAction.ShowLoading -> {
               // showProgress(action.show)
                if (action.show) {
                    hideKeyboard()
                }
            }

            is SupportAction.ShowFailureMsg -> action.message?.let {
                showToast(requireContext(), action.message)
           //     showProgress(false)

            }
            is SupportAction.ShowTerms -> {

                binding.tvTerms.setText(action.data?.content.toString())

            }


            else -> {

            }
        }
    }

    private fun onClick() {
        binding.toolbar.tvTitle.setText(resources.getString(R.string.rights_and_terms))
        binding.toolbar.ivMenu.isVisible = false
        binding.toolbar.ivBack.setOnClickListener {
             dismiss()
        }
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window?.setLayout(width, height)
      //  dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window?.setGravity(Gravity.BOTTOM)

    }
   // override fun getTheme() = R.style.CustomBottomSheetDialogTheme

}