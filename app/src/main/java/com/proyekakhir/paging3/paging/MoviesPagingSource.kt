package com.proyekakhir.paging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.proyekakhir.paging3.data.network.Resource
import com.proyekakhir.paging3.data.network.response.ResultsItem
import com.proyekakhir.paging3.repository.RepositoryMovies
import kotlinx.coroutines.flow.collect
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MoviesPagingSource @Inject constructor(private val repositoryMovies: RepositoryMovies) :
    PagingSource<Int, ResultsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        val position = params.key ?: 1

        return try {
            val response = ArrayList<ResultsItem>()
            repositoryMovies.getPopularMovies(page = position).collect {
                when (it) {
                    is Resource.Success -> {
                        response.addAll(it.value.results!!)
                    }
                    else -> Unit
                }
            }
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + 1
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}