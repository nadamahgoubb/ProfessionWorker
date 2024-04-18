package com.horizon.professionworker.data.dataSource.paging

 import com.horizon.professionworker.data.response.ServicesItemsResponse
import com.horizon.professionworker.base.*
import com.horizon.professionworker.data.repo.Repository


class ServiceHomePagingSource(private val repo: Repository,
                              private val params: PagingParams
) : BasePagingDataSource<ServicesItemsResponse>() {


    override val queryParams: PagingParams = params

    override suspend fun execute(): NetworkResponse<BasePagingResponse<ServicesItemsResponse>, ErrorResponse> {
        var data =repo.getServices(params.page)
        return data
    }

}