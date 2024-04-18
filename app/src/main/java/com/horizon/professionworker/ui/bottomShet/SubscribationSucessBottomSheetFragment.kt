package com.horizon.professionworker.ui.bottomShet

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.horizon.professionworker.R
import com.horizon.professionworker.databinding.BottomFragmentSubscribeationSheetBinding
import com.horizon.professionworker.databinding.BottomSheetSubscribationSucessBinding
import com.horizon.professionworker.ui.activity.MainActivity
import com.horizon.professionworker.util.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
interface OnClickSucessBottomSheetFragment{
    fun onClick(choice: String)
}
class SubscribationSucessBottomSheetFragment  (var onClick: OnClickSucessBottomSheetFragment) :
BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetSubscribationSucessBinding

    companion object {
        fun newInstance(onClick:OnClickSucessBottomSheetFragment): SubscribationSucessBottomSheetFragment {
            val args = Bundle()
            val f = SubscribationSucessBottomSheetFragment(onClick)
            f.arguments = args
            return f
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = BottomSheetSubscribationSucessBinding.inflate(inflater)
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