package com.horizon.professionworker.ui.listener

import com.horizon.professionworker.data.response.ServicesItemsResponse
import com.horizon.professionworker.data.response.SubServiceItemsResponse

interface ServiceOnClickListener {
    fun onServiceClickListener(item: ServicesItemsResponse?)
}

interface SubServiceListener {
    fun onSubServiceClickListener(items: ArrayList<SubServiceItemsResponse>)
}
