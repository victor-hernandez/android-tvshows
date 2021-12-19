package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.victorhernandez.tvshows.domain.usecase.GetTopRatedShowsUseCase
import dev.victorhernandez.tvshows.presentation.flow.collect
import dev.victorhernandez.tvshows.presentation.flow.flowStatus
import dev.victorhernandez.tvshows.presentation.ktx.append
import dev.victorhernandez.tvshows.presentation.mapper.toListUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedTvShowsViewModel @Inject constructor(
    private val getTopRatedShowsUseCase: GetTopRatedShowsUseCase,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TopRatedTvShowsUiState())
    val uiState = _uiState.asStateFlow()

    private var currentPage = 1
    private var canLoadMoreShows = false

    init {
        loadTopRatedTvShows()
    }

    private fun loadTopRatedTvShows() {
        currentPage = 1
        _uiState.value = _uiState.value.copy(shows = emptyList())
        loadTopRatedTvShows(currentPage)
    }

    fun loadMoreShows() {
        if (!_uiState.value.loading && canLoadMoreShows) {
            loadTopRatedTvShows(currentPage + 1)
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
                                shows = state.shows.append(it.toListUiModel())
                            )
                        }
                        TopRatedTvShowsUiState(
                            shows = it.toListUiModel()
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

}