package com.proyekakhir.paging3.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.proyekakhir.paging3.data.network.response.ResultsItem
import com.proyekakhir.paging3.paging.MoviesPagingSource
import com.proyekakhir.paging3.repository.RepositoryMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseMovies @Inject constructor(private val repositoryMovies: RepositoryMovies) : IUseCase {
    override suspend fun getMovies(): Flow<PagingData<ResultsItem>> =  Pager(
        config = PagingConfig(
            pageSize =5,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MoviesPagingSource(repositoryMovies) }
    ).flow
}