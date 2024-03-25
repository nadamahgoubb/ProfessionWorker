package com.dot_jo.profession.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
 import androidx.recyclerview.widget.RecyclerView

import com.dot_jo.professionworker.data.response.OrdersItem
import com.dot_jo.professionworker.databinding.ItemOrdersBinding
import com.dot_jo.professionworker.util.Constants
import com.dot_jo.professionworker.util.Utils.toTwelevePattern


interface OrdersClickListener {

    fun onOrderDetailsClicked(item: OrdersItem?)
    fun onOrderCallClicked(item: OrdersItem?)
    fun onOrderChatClicked(item: OrdersItem?)


}

class OrdersAdapter(
    var state: String, var listener: OrdersClickListener
) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    var _binding: ItemOrdersBinding? = null
    var list = mutableListOf<OrdersItem>()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        var currentItem = list.get(position)
        holder.binding.tvOrderId.text = "# " + currentItem?.orderId
         holder.binding.tvTime.text =
             toTwelevePattern (currentItem?.orderTime.toString()) + " " + currentItem?.orderDate.toString()

        holder.binding.root.setOnClickListener {
            listener.onOrderDetailsClicked(currentItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {

        _binding = ItemOrdersBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        if (state == Constants.PREV_ORDER) _binding?.tvStatusCompelted?.isVisible = true
        else if (state == Constants.New_ORDER) _binding?.tvStatusNewOrder?.isVisible =
            true
        else if (state == Constants.CURRENT_ORDER) _binding?.tvStatusInProgress?.isVisible = true
        return OrdersViewHolder(_binding!!)
    }


    class OrdersViewHolder(var binding: ItemOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    override fun getItemCount(): Int = list.size
}


