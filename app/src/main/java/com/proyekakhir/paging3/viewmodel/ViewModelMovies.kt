package com.proyekakhir.paging3.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.proyekakhir.paging3.data.network.response.ResultsItem
import com.proyekakhir.paging3.usecase.IUseCase
import com.proyekakhir.paging3.usecase.UseCaseMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ViewModelMovies @Inject constructor(private val useCaseMovies: UseCaseMovies) : IUseCase,
    ViewModel() {
    override suspend fun getMovies(): Flow<PagingData<ResultsItem>> {
        return useCaseMovies.getMovies().cachedIn(viewModelScope)
    }
}