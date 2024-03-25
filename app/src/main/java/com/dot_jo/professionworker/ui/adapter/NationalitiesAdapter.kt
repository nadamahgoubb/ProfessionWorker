package com.dot_jo.professionworker.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dot_jo.professionworker.data.response.CitesItemsResponse
import com.dot_jo.professionworker.data.response.NationalitiesItemResponse
import com.dot_jo.professionworker.databinding.ItemFilterMultiChoiceBinding


interface NationalitiesListener {

    fun onItemClicked(item: NationalitiesItemResponse?)

}


class NationalitiesAdapter(
    var listener: NationalitiesListener
) :
     RecyclerView.Adapter<NationalitiesAdapter.NationalitiesViewHolder>() {
    lateinit var context: Context
    var lastPosition = -1
     var list = mutableListOf<NationalitiesItemResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: NationalitiesViewHolder, position: Int) {
        var currentItem = list.get(position)
        holder.binding.tvName.text = currentItem?.name

        if (currentItem.choosen) holder.binding.checkbox.isChecked = true
        else holder.binding.checkbox.isChecked = false

        holder.binding.checkbox.setOnClickListener {
            if (holder.binding.checkbox.isChecked == true) {
                currentItem?.choosen = true
                //  currentItem?.let { it1 -> selectOneItemOnly(it1, position) }
                currentItem?.let { it1 -> listener.onItemClicked(it1) }
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

                currentItem?.let { it1 -> listener.onItemClicked(it1) }
                currentItem?.choosen = true
                // listener.onOrderClicked(null)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NationalitiesViewHolder {

        return NationalitiesViewHolder(
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

    class NationalitiesViewHolder(var binding: ItemFilterMultiChoiceBinding) :
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


