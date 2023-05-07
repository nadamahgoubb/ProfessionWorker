 package com.example.professionworker.data.dataSource.paging

import com.example.profession.data.dataSource.response.SubServiceItemsResponse
import com.example.professionworker.base.*
import com.example.professionworker.data.params.SubServicesParams
import com.example.professionworker.data.repo.Repository


 class SubServiceHomePagingSource(private val repo: Repository,
                                  private val params: SubServicesParams
) : BasePagingDataSource<SubServiceItemsResponse>() {


    override val queryParams: PagingParams = params

    override suspend fun execute(): NetworkResponse<BasePagingResponse<SubServiceItemsResponse>, ErrorResponse> {
        var data =repo.getSubServiceItemsResponse(params.page , params.service_id)
        return data
    }

}