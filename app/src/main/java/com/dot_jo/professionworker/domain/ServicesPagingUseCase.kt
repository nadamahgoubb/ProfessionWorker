package com.dot_jo.professionworker.domain


import androidx.paging.PagingSource

import com.dot_jo.professionworker.data.response.ServicesItemsResponse
import com.dot_jo.professionworker.base.BasePagingUseCase
import com.dot_jo.professionworker.base.PagingParams
import com.dot_jo.professionworker.data.dataSource.paging.ServiceHomePagingSource
import com.dot_jo.professionworker.data.repo.Repository

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ServicesPagingUseCase @Inject constructor(private val repo: Repository) :
    BasePagingUseCase<ServicesItemsResponse, PagingParams>() {

    override fun getPagingSource(params: PagingParams?): PagingSource<Int, ServicesItemsResponse> {
       var res= ServiceHomePagingSource(repo, params!!)
        return res


    }
}
