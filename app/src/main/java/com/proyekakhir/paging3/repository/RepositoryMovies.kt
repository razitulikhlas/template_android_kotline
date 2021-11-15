package com.proyekakhir.paging3.repository

import com.proyekakhir.paging3.data.network.RemoteDataSource
import com.proyekakhir.paging3.data.network.Resource
import com.proyekakhir.paging3.data.network.response.ResponseMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryMovies @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IRepositoryMovies {

    override suspend fun getPopularMovies(page: Int): Flow<Resource<ResponseMovies>> {
        return remoteDataSource.getMoviesPopular(page)
    }
}