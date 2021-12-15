package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.victorhernandez.tvshows.domain.usecase.GetTopRatedShowsUseCase
import dev.victorhernandez.tvshows.presentation.flow.collect
import dev.victorhernandez.tvshows.presentation.flow.flowStatus
import dev.victorhernandez.tvshows.presentation.mapper.toUi
import dev.victorhernandez.tvshows.presentation.model.TvShowListUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopRatedTvShowsViewModel(
    private val getTopRatedShowsUseCase: GetTopRatedShowsUseCase,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvShowListUiState())
    val uiState = _uiState.asStateFlow()

    private var currentPage = 1
    private var canLoadMoreShows = false

    fun loadTopRatedTvShows() {
        currentPage = 1
        loadTopRatedTvShows(currentPage)
    }

    fun loadMoreShows() {
        if (canLoadMoreShows) {
            loadTopRatedTvShows(currentPage)
        }
    }

    private fun loadTopRatedTvShows(page: Int) {
        viewModelScope.launch(coroutineDispatcher) {
            flowStatus {
                getTopRatedShowsUseCase.execute(GetTopRatedShowsUseCase.Params(page))
            }
                .collect(
                    onSuccess = {
                        currentPage = page
                        canLoadMoreShows = page < it.totalPages

                        _uiState.value = _uiState.value.let { state ->
                            state.copy(
                                shows = state.shows.append(it.toUi())
                            )
                        }
                            TvShowListUiState(
                            shows = it.toUi()
                        )
                    },
                    onError = {
                        canLoadMoreShows = true
                    },
                    onLoading = {
                        _uiState.value = _uiState.value.copy(loading = it)
                    }
                )
        }
    }

    private fun <T> List<T>?.append(items: Collection<T>?): List<T> =
        (this?.toMutableList() ?: mutableListOf()).apply { addAll(items ?: listOf()) }

}