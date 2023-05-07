package com.example.professionworker.data.dataSource.paging

 import com.example.profession.data.dataSource.response.ServicesItemsResponse
import com.example.professionworker.base.*
import com.example.professionworker.data.repo.Repository


class ServiceHomePagingSource(private val repo: Repository,
                              private val params: PagingParams
) : BasePagingDataSource<ServicesItemsResponse>() {


    override val queryParams: PagingParams = params

    override suspend fun execute(): NetworkResponse<BasePagingResponse<ServicesItemsResponse>, ErrorResponse> {
        var data =repo.getServices(params.page)
        return data
    }

}