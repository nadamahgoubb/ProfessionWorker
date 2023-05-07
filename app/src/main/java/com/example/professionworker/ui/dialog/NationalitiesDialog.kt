package com.example.professionworker.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.profession.data.dataSource.response.BankItemResponse
import com.example.profession.data.dataSource.response.CitesItemsResponse
import com.example.profession.data.dataSource.response.NationalitiesItemResponse

import com.example.professionworker.util.ext.init
import com.example.professionworker.R
import com.example.professionworker.databinding.DialogCitiesBinding
import com.example.professionworker.ui.adapter.*

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NationalitiesDialog(
    private val onClick: NationalitiesListener,
    var data: ArrayList<NationalitiesItemResponse>,
) : DialogFragment(R.layout.dialog_cities),   NationalitiesListener {

    private lateinit var binding: DialogCitiesBinding
    private lateinit var adapter: NationalitiesAdapter


    companion object {
        fun newInstance(
            onClick: NationalitiesListener,
            data: ArrayList<NationalitiesItemResponse>,
        ): NationalitiesDialog {
            val args = Bundle()
            val f = NationalitiesDialog(
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

        adapter = NationalitiesAdapter(  this)
        binding.rvCat.init(requireContext(), adapter, 2)
        adapter.list = (data)
        adapter.notifyDataSetChanged()
    }


    override fun onItemClicked(item: NationalitiesItemResponse?) {
         onClick.onItemClicked(item)
        dialog?.dismiss()    }
}
