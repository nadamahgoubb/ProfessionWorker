package com.dot_jo.professionworker.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
 import com.dot_jo.professionworker.data.response.SubServiceItemsResponse
import com.dot_jo.professionworker.databinding.ItemCheckoutSubserviceBinding
import com.dot_jo.professionworker.util.ext.loadImage


class CheckoutSubserviceAdapter( var service:String
  ) : RecyclerView.Adapter<CheckoutSubserviceAdapter.ChooseTimeViewHolder>() {

    var _binding: ItemCheckoutSubserviceBinding? = null

    var itemsList = mutableListOf<SubServiceItemsResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseTimeViewHolder {
        _binding = ItemCheckoutSubserviceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChooseTimeViewHolder(_binding!!)
    }


    override fun onBindViewHolder(holder: ChooseTimeViewHolder, position: Int) {
         var currentItem = itemsList[position]
       holder.binding.tvService.text =service
       holder.binding.tvServiceDesc.text =currentItem.name
        holder.binding.ivLogo.loadImage( currentItem?.icon)

    }




    override fun getItemCount(): Int =itemsList.size

    class ChooseTimeViewHolder(val binding: ItemCheckoutSubserviceBinding) :
        RecyclerView.ViewHolder(binding.root)


}


