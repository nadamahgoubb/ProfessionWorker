package com.example.professionworker.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment

import com.example.professionworker.R
import com.example.professionworker.databinding.DialogChooseProviderTypeBinding
import com.example.professionworker.databinding.DialogDeleteAccountSheetBinding
import com.example.professionworker.ui.activity.AuthActivity
import com.example.professionworker.ui.activity.MainActivity
import com.example.professionworker.util.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

interface OnProviderClick {
    fun onClick(choice: String)
}

class ProviderTypeSheetFragment(var onClick: OnProviderClick) : DialogFragment() {

    private lateinit var binding: DialogChooseProviderTypeBinding
    private lateinit var parent: MainActivity

    companion object {
        fun newInstance(onClick: OnProviderClick): ProviderTypeSheetFragment {
            val args = Bundle()
            val f = ProviderTypeSheetFragment(onClick)
            f.arguments = args
            return f
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DialogChooseProviderTypeBinding.inflate(inflater)
        parent = requireActivity() as MainActivity
        onClick()
        return binding.root
    }

    private fun onClick() {
        binding.tvCompany.setOnClickListener {
            onClick.onClick(Constants.COMPANY)
            dismiss()
        }
        binding.tvIndividual.setOnClickListener {
            onClick.onClick(Constants.PERSON)
            dismiss()

        }
    }

    @SuppressLint("CutPasteId")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val bottomSheet =
                bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet!!)
            behavior.skipCollapsed = true
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        dialog!!.window?.setLayout(width, height)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window?.setGravity( Gravity.CENTER )
     //   setLayoutMarginBottom(binding.root, 300f)


        onClick()

    }

   /* fun setLayoutMarginBottom(view: View, bottom: Float) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.bottomMargin = bottom.toInt()
        view.layoutParams = layoutParams
    }*/

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

}