package com.dot_jo.professionworker.data.dataSource.paging

 import com.dot_jo.professionworker.data.response.ServicesItemsResponse
import com.dot_jo.professionworker.base.*
import com.dot_jo.professionworker.data.repo.Repository


class ServiceHomePagingSource(private val repo: Repository,
                              private val params: PagingParams
) : BasePagingDataSource<ServicesItemsResponse>() {


    override val queryParams: PagingParams = params

    override suspend fun execute(): NetworkResponse<BasePagingResponse<ServicesItemsResponse>, ErrorResponse> {
        var data =repo.getServices(params.page)
        return data
    }

}