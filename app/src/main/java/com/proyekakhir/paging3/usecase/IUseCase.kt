package com.proyekakhir.paging3.usecase

import androidx.paging.PagingData
import com.proyekakhir.paging3.data.network.response.ResultsItem
import kotlinx.coroutines.flow.Flow


interface IUseCase {
    suspend fun getMovies(): Flow<PagingData<ResultsItem>>
}