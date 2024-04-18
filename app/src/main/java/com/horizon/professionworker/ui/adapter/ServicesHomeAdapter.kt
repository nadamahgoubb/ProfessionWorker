package com.horizon.profession.ui.adapter

  import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.horizon.professionworker.data.response.ServicesItemsResponse
import com.horizon.professionworker.databinding.ItemFilterMultiChoiceBinding
import com.horizon.professionworker.ui.listener.ServiceOnClickListener


class ServicesHomeAdapter(
    var listener : ServiceOnClickListener,
 //) : RecyclerView.Adapter<ServicesHomeAdapter.ServiceHomeViewHolder>() {
): PagingDataAdapter<ServicesItemsResponse, ServicesHomeAdapter.ServiceHomeViewHolder>(
    Service_DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ServiceHomeViewHolder, position: Int) {
 holder.binding.tvName.setText(getItem(position)?.name)
   //    holder.binding.ivService.setImageDrawable(context.getDrawable(R.drawable.gr2))

        holder.binding.root.setOnClickListener {
            holder.binding.checkbox.isChecked= true
            listener.onServiceClickListener(getItem(position))
            //currentItem)
        }
        holder.binding.checkbox.setOnClickListener {
            listener.onServiceClickListener(getItem(position))
            //currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHomeViewHolder {

        return ServiceHomeViewHolder(
            ItemFilterMultiChoiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    class ServiceHomeViewHolder(val binding: ItemFilterMultiChoiceBinding) :
        RecyclerView.ViewHolder(binding.root)
    

    companion object {
        private val Service_DIFF_CALLBACK = object : DiffUtil.ItemCallback<ServicesItemsResponse>() {
            override fun areItemsTheSame(oldItem: ServicesItemsResponse, newItem: ServicesItemsResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ServicesItemsResponse, newItem: ServicesItemsResponse): Boolean =
                oldItem == newItem
        }
    }
}