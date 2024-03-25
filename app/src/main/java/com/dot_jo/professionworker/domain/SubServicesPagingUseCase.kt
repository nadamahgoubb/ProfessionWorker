package com.dot_jo.professionworker.domain


import androidx.paging.PagingSource
import com.dot_jo.professionworker.data.response.SubServiceItemsResponse
import com.dot_jo.professionworker.base.BasePagingUseCase
import com.dot_jo.professionworker.base.PagingParams
import com.dot_jo.professionworker.data.dataSource.paging.SubServiceHomePagingSource
import com.dot_jo.professionworker.data.params.SubServicesParams
import com.dot_jo.professionworker.data.repo.Repository


import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SubServicesPagingUseCase @Inject constructor(private val repo: Repository) :
    BasePagingUseCase<SubServiceItemsResponse, PagingParams>() {

    override fun getPagingSource(params: PagingParams?): PagingSource<Int, SubServiceItemsResponse> {
       var res= SubServiceHomePagingSource(repo, params!! as SubServicesParams)
        return res


    }
}
