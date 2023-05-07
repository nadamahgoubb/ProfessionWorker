package com.example.professionworker.domain


import androidx.paging.PagingSource

import com.example.profession.data.dataSource.response.ServicesItemsResponse
import com.example.professionworker.base.BasePagingUseCase
import com.example.professionworker.base.PagingParams
import com.example.professionworker.data.dataSource.paging.ServiceHomePagingSource
import com.example.professionworker.data.repo.Repository

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
