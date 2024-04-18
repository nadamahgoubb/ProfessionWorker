package com.horizon.professionworker.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.horizon.professionworker.data.response.BankItemResponse
import com.horizon.professionworker.data.response.CitesItemsResponse
 import com.horizon.professionworker.databinding.ItemFilterMultiChoiceBinding
import com.horizon.professionworker.ui.dialog.BankListener


class BanksAdapter(
    var listener: BankListener
) :
     RecyclerView.Adapter<BanksAdapter.BanksViewHolder>() {
    lateinit var context: Context
    var lastPosition = -1
    var _binding: ItemFilterMultiChoiceBinding? = null
    var list = mutableListOf<BankItemResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: BanksViewHolder, position: Int) {
        var currentItem = list.get(position)
        holder.binding.tvName.text = currentItem?.name

        if (currentItem.choosen) holder.binding.checkbox.isChecked = true
        else holder.binding.checkbox.isChecked = false

        holder.binding.checkbox.setOnClickListener {
            if (holder.binding.checkbox.isChecked == true) {
                currentItem?.choosen = true
                //  currentItem?.let { it1 -> selectOneItemOnly(it1, position) }
                currentItem?.let { it1 -> listener.onBankClicked(it1) }
            } else {
                currentItem?.choosen = false
                // listener.onOrderClicked(null)

            }
        }
        holder.binding.root.setOnClickListener {
            if (holder.binding.checkbox.isChecked == true) {
                holder.binding.checkbox.isChecked = false
                currentItem?.choosen = false
            } else {
                holder.binding.checkbox.isChecked = true

                currentItem?.let { it1 -> listener.onBankClicked(it1) }
                currentItem?.choosen = true
                // listener.onOrderClicked(null)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksViewHolder {

        return BanksViewHolder(
            ItemFilterMultiChoiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    fun updateItem(item: CitesItemsResponse, pos: Int) {
        //    ca.indexOfFirst { item.id == it.id }
        //       .takeIf { it > -1 }?.let { pos ->
        //           list[pos] = item
        list.get(pos)?.name = item.name
        list.get(pos)?.id = item.id
        //   getItem(pos)?.countryId=item.countryId
        list.get(pos)?.choosen = item.choosen

        notifyItemChanged(pos)
    }


    fun selectOneItemOnly(item: CitesItemsResponse, position: Int) {
        if (lastPosition != -1) {
            var lastChoosen = list.get(lastPosition)

            updateItem(
                item = CitesItemsResponse(
                    lastChoosen?.id,
                    lastChoosen?.name,
                    //     lastChoosen?.countryId,
                    false
                ), position
            )
        }
        updateItem(item, position)
        lastPosition = position


    }

    class BanksViewHolder(var binding: ItemFilterMultiChoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object {
        private val Cities_DIFF_CALLBACK = object : DiffUtil.ItemCallback<CitesItemsResponse>() {
            override fun areItemsTheSame(
                oldItem: CitesItemsResponse,
                newItem: CitesItemsResponse
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CitesItemsResponse,
                newItem: CitesItemsResponse
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int = list.size
}


