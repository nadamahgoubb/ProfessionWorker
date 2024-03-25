package com.dot_jo.professionworker.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dot_jo.professionworker.R
import com.dot_jo.professionworker.databinding.DialogAccountBinding
import com.dot_jo.professionworker.util.Constants.COMPANY
import com.dot_jo.professionworker.util.Constants.PERSON
import dagger.hilt.android.AndroidEntryPoint

/*

interface AccountsTypeListener {
     fun onAccountsTypeClicked(item: AccountType?)
}
data class AccountType(
    val name :String,
    val id:String
)

@AndroidEntryPoint
class AccountsTypeDialog(
    private val listener: AccountsTypeListener,
 ) : DialogFragment(R.layout.dialog_account)    {

    private lateinit var binding: DialogAccountBinding

    companion object {
        fun newInstance(
            onClick: AccountsTypeListener,
         ): AccountsTypeDialog {
            val args = Bundle()
            val f = AccountsTypeDialog(
                onClick )
            f.arguments = args
            return f
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (dialog?.isShowing == false) {
            binding = DialogAccountBinding.inflate(inflater)
            return binding.root
        } else return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        dialog!!.window?.setLayout(width, height)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window?.setGravity(Gravity.BOTTOM)
        setLayoutHorizintalBottom(binding.root, 100f)

        onClick()


    }

    fun setLayoutHorizintalBottom(view: View, bottom: Float) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.rightMargin = bottom.toInt()
        layoutParams.leftMargin = bottom.toInt()
        view.layoutParams = layoutParams
    }


    private fun onClick() {

      binding.checkbox.setOnClickListener {
          listener.onAccountsTypeClicked(AccountType(resources.getString(R.string.person),PERSON))
          dismiss() }

      binding.lytPerson.setOnClickListener {
          binding.checkbox.isChecked= true
          listener.onAccountsTypeClicked(AccountType(resources.getString(R.string.person),PERSON))
          dismiss() }

        binding.checkboxCompany.setOnClickListener {
          listener.onAccountsTypeClicked(AccountType(resources.getString(R.string.company), COMPANY))
            dismiss() }

      binding.lytCompany.setOnClickListener {
          binding.checkboxCompany.isChecked= true
          listener.onAccountsTypeClicked(AccountType(resources.getString(R.string.company), COMPANY))
          dismiss() }
    }


}
*/
