package com.horizon.professionworker.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import androidx.fragment.app.DialogFragment
import com.horizon.professionworker.R
import com.horizon.professionworker.databinding.DialogChooseProviderTypeBinding
import com.horizon.professionworker.ui.activity.MainActivity
import com.horizon.professionworker.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

interface OnProviderClick {
    fun onClick(choice: String)
}
@AndroidEntryPoint
class ProviderTypeSheetFragment(var onClick: OnProviderClick) : DialogFragment() {

    private lateinit var binding: DialogChooseProviderTypeBinding

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