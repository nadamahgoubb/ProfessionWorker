package com.example.profession.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
 import com.example.profession.data.dataSource.response.SubServiceItemsResponse

import com.example.professionworker.databinding.ItemFilterMultiChoiceBinding
import com.example.professionworker.ui.listener.SubServiceListener


class SubServiceAdapter(
    var listener: SubServiceListener
) : PagingDataAdapter<SubServiceItemsResponse, SubServiceAdapter.MyViewHolder>(ORDER_DIFF_CALLBACK) {
    var selectedItems: ArrayList<SubServiceItemsResponse> = arrayListOf()
     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = getItem(position)
        holder.binding.tvName.text = currentItem?.name
holder.binding.checkbox.isChecked = currentItem?.choosen == true
        holder.binding.root.setOnClickListener {
            if (holder.binding.checkbox.isChecked) {
                holder.binding.checkbox.isChecked = false
                selectedItems.remove(currentItem)
                currentItem?.choosen = false
             //   holder.binding.tvTitle.setTextColor(context.resources.getColor(R.color.black))
            } else {
                holder.binding.checkbox.isChecked = true
                currentItem?.let { it1 -> selectedItems.add(it1) }
                currentItem?.choosen = true
           //     holder.binding.tvTitle.setTextColor(context.resources.getColor(R.color.gold))
            }
            currentItem?.let { it1 -> listener.onSubServiceClickListener(selectedItems) }
        }
        holder.binding.checkbox.setOnClickListener {
            if (holder.binding.checkbox.isChecked) {
                currentItem?.let { it1 -> selectedItems.add(it1) }
                currentItem?.choosen = true
         //       holder.binding.tvTitle.setTextColor(context.resources.getColor(R.color.gold))
            } else {
                selectedItems.remove(currentItem)
                currentItem?.choosen = false
      //          holder.binding.tvTitle.setTextColor(context.resources.getColor(R.color.black))

            }
            currentItem?.let { it1 -> listener.onSubServiceClickListener(selectedItems) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            ItemFilterMultiChoiceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    class MyViewHolder(var binding: ItemFilterMultiChoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    companion object {
        private val ORDER_DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<SubServiceItemsResponse>() {
                override fun areItemsTheSame(
                    oldItem: SubServiceItemsResponse, newItem: SubServiceItemsResponse
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: SubServiceItemsResponse, newItem: SubServiceItemsResponse
                ): Boolean = oldItem == newItem
            }
    }
}


