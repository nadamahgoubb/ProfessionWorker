package com.example.professionworker.domain


import androidx.paging.PagingSource
import com.example.profession.data.dataSource.response.SubServiceItemsResponse
import com.example.professionworker.base.BasePagingUseCase
import com.example.professionworker.base.PagingParams
import com.example.professionworker.data.dataSource.paging.SubServiceHomePagingSource
import com.example.professionworker.data.params.SubServicesParams
import com.example.professionworker.data.repo.Repository


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
