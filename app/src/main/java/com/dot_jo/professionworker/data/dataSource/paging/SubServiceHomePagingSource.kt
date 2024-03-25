 package com.dot_jo.professionworker.data.dataSource.paging

import com.dot_jo.professionworker.data.response.SubServiceItemsResponse
import com.dot_jo.professionworker.base.*
import com.dot_jo.professionworker.data.params.SubServicesParams
import com.dot_jo.professionworker.data.repo.Repository


 class SubServiceHomePagingSource(private val repo: Repository,
                                  private val params: SubServicesParams
) : BasePagingDataSource<SubServiceItemsResponse>() {


    override val queryParams: PagingParams = params

    override suspend fun execute(): NetworkResponse<BasePagingResponse<SubServiceItemsResponse>, ErrorResponse> {
        var data =repo.getSubServiceItemsResponse(params.page , params.service_id)
        return data
    }

}