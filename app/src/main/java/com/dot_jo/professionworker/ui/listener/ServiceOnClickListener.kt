package com.dot_jo.professionworker.ui.listener

import com.dot_jo.professionworker.data.response.ServicesItemsResponse
import com.dot_jo.professionworker.data.response.SubServiceItemsResponse

interface ServiceOnClickListener {
    fun onServiceClickListener(item: ServicesItemsResponse?)
}

interface SubServiceListener {
    fun onSubServiceClickListener(items: ArrayList<SubServiceItemsResponse>)
}
