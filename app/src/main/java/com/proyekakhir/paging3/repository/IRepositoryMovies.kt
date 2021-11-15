package com.proyekakhir.paging3.repository

import com.proyekakhir.paging3.data.network.Resource
import com.proyekakhir.paging3.data.network.response.ResponseMovies
import kotlinx.coroutines.flow.Flow

interface IRepositoryMovies {
    suspend fun getPopularMovies(page:Int): Flow<Resource<ResponseMovies>>
}