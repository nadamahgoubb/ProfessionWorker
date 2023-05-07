package com.example.professionworker.ui.listener

import com.example.profession.data.dataSource.response.ServicesItemsResponse
import com.example.profession.data.dataSource.response.SubServiceItemsResponse

interface ServiceOnClickListener {
    fun onServiceClickListener(item: ServicesItemsResponse?)
}

interface SubServiceListener {
    fun onSubServiceClickListener(items: ArrayList<SubServiceItemsResponse>)
}
