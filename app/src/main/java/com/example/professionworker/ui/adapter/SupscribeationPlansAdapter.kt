package com.example.professionworker.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.professionworker.R
import com.example.professionworker.domain.response.SubscibationPlanResponse
import com.example.professionworker.databinding.ItemSubscribationPlansBinding
import com.example.professionworker.ui.listener.SubscribationPlansOnClickListener


class SupscribeationPlansAdapter(
    var listener : SubscribationPlansOnClickListener,
    var   context: Context
) : RecyclerView.Adapter<SupscribeationPlansAdapter.InProgressCompeletedViewHolder>() {

     var _binding: ItemSubscribationPlansBinding? = null

    var itemsList = mutableListOf<SubscibationPlanResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InProgressCompeletedViewHolder {
        _binding = ItemSubscribationPlansBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InProgressCompeletedViewHolder(_binding!!)
    }


    override fun onBindViewHolder(holder: InProgressCompeletedViewHolder, position: Int) {
     //   var currentItem = itemsList[position]
      if(position ==0  ){

      }
         else {
          holder.binding.tvPrice.setText(context.resources.getString(R.string.free))
          holder.binding.tvDesc.setText(context.resources.getString(R.string.free_desc))
      }
        holder.binding.root.setOnClickListener {
            listener.onPlanClickListener()
        }
    }


    fun deleteItem(position: Int) {
        itemsList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemsList.size)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = 2

    class InProgressCompeletedViewHolder(val binding: ItemSubscribationPlansBinding) :
        RecyclerView.ViewHolder(binding.root)


}


