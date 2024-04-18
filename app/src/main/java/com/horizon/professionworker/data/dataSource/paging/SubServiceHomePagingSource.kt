 package com.horizon.professionworker.data.dataSource.paging

import com.horizon.professionworker.data.response.SubServiceItemsResponse
import com.horizon.professionworker.base.*
import com.horizon.professionworker.data.params.SubServicesParams
import com.horizon.professionworker.data.repo.Repository


 class SubServiceHomePagingSource(private val repo: Repository,
                                  private val params: SubServicesParams
) : BasePagingDataSource<SubServiceItemsResponse>() {


    override val queryParams: PagingParams = params

    override suspend fun execute(): NetworkResponse<BasePagingResponse<SubServiceItemsResponse>, ErrorResponse> {
        var data =repo.getSubServiceItemsResponse(params.page , params.service_id)
        return data
    }

}