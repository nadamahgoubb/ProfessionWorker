package com.horizon.professionworker.domain


import androidx.paging.PagingSource
import com.horizon.professionworker.data.response.SubServiceItemsResponse
import com.horizon.professionworker.base.BasePagingUseCase
import com.horizon.professionworker.base.PagingParams
import com.horizon.professionworker.data.dataSource.paging.SubServiceHomePagingSource
import com.horizon.professionworker.data.params.SubServicesParams
import com.horizon.professionworker.data.repo.Repository


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
