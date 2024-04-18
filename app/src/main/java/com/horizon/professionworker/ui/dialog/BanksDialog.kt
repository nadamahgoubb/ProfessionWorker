package com.horizon.professionworker.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.horizon.professionworker.data.response.BankItemResponse

import com.horizon.professionworker.util.ext.init
import com.horizon.professionworker.R
import com.horizon.professionworker.databinding.DialogCitiesBinding
import com.horizon.professionworker.ui.adapter.BanksAdapter

import dagger.hilt.android.AndroidEntryPoint


interface BankListener {
     fun onBankClicked(item: BankItemResponse?)
}


@AndroidEntryPoint
class BanksDialog(
    private val onClick: BankListener,
    var data: ArrayList<BankItemResponse>,
) : DialogFragment(R.layout.dialog_cities), BankListener {

    private lateinit var binding: DialogCitiesBinding
    private lateinit var adapter: BanksAdapter
    var cityItem: BankItemResponse? = null


    companion object {
        fun newInstance(
            onClick: BankListener,
            data: ArrayList<BankItemResponse>,
        ): BanksDialog {
            val args = Bundle()
            val f = BanksDialog(
                onClick, data)
            f.arguments = args
            return f
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (dialog?.isShowing == false) {
            binding = DialogCitiesBinding.inflate(inflater)
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


    }

    fun setLayoutHorizintalBottom(view: View, bottom: Float) {
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.rightMargin = bottom.toInt()
        layoutParams.leftMargin = bottom.toInt()
        view.layoutParams = layoutParams
    }


    private fun initAdapters() {

        adapter = BanksAdapter(  this)
        binding.rvCat.init(requireContext(), adapter, 2)
        adapter.list = (data)
        adapter.notifyDataSetChanged()
    }

    override fun onBankClicked(item: BankItemResponse?) {
        cityItem = item
        onClick.onBankClicked(item)
        dialog?.dismiss()
    }
}
