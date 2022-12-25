package com.example.professionworker.ui.bottomShet

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.professionworker.R
import com.example.professionworker.databinding.BottomFragmentSubscribeationSheetBinding
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.util.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

interface OnClickSubscribationBottomSheetFragment{
    fun onClick(choice: String)
}

class SubscribationBottomSheetFragment(var onClick: OnClickSubscribationBottomSheetFragment) :
    BottomSheetDialogFragment() {

    private lateinit var binding: BottomFragmentSubscribeationSheetBinding

    companion object {
        fun newInstance(onClick:OnClickSubscribationBottomSheetFragment): SubscribationBottomSheetFragment {
            val args = Bundle()
            val f = SubscribationBottomSheetFragment(onClick)
            f.arguments = args
            return f
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = BottomFragmentSubscribeationSheetBinding.inflate(inflater)
         onClick()
        return binding.root
    }

    private fun onClick() {
        binding.btnOk.setOnClickListener {
            onClick.onClick(Constants.YES)
            dismiss()
        }
    }

    @SuppressLint("CutPasteId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val bottomSheet =
                bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior: BottomSheetBehavior<*> =
                BottomSheetBehavior.from(bottomSheet!!)
            behavior.skipCollapsed = true
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

}