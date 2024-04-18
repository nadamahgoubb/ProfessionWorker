package com.horizon.professionworker.domain


import androidx.paging.PagingSource

import com.horizon.professionworker.data.response.ServicesItemsResponse
import com.horizon.professionworker.base.BasePagingUseCase
import com.horizon.professionworker.base.PagingParams
import com.horizon.professionworker.data.dataSource.paging.ServiceHomePagingSource
import com.horizon.professionworker.data.repo.Repository

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
