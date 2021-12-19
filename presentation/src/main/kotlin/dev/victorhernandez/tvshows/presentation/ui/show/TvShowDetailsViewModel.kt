package dev.victorhernandez.tvshows.presentation.ui.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.victorhernandez.tvshows.domain.usecase.GetSimilarTvShowsUseCase
import dev.victorhernandez.tvshows.presentation.flow.collect
import dev.victorhernandez.tvshows.presentation.flow.flowStatus
import dev.victorhernandez.tvshows.presentation.ktx.append
import dev.victorhernandez.tvshows.presentation.mapper.toDetailUiModel
import dev.victorhernandez.tvshows.presentation.mapper.toListUiModel
import dev.victorhernandez.tvshows.presentation.model.TvShowDetailUiModel
import dev.victorhernandez.tvshows.presentation.ui.shows.TopRatedTvShowsUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvShowDetailsUiState())
    val uiState = _uiState.asStateFlow()

    private var show: TvShowDetailUiModel? = null
    private var currentPage = 1
    private var canLoadMoreShows = true

    fun init(show: TvShowDetailUiModel) {
        if (this.show == show) return

        this.show = show
        currentPage = 1
        _uiState.value = _uiState.value.let { state ->
            state.copy(
                shows = listOf(show)
            )
        }
        loadRelatedTvShows(show.id, currentPage)
    }

    fun loadNextSimilarTvShows() {
        show?.let { show ->
            if (!_uiState.value.loading && canLoadMoreShows) {
                loadRelatedTvShows(show.id, currentPage+1)
            }
        }
    }

    private fun loadRelatedTvShows(showId: Int, page: Int) {
        viewModelScope.launch(coroutineDispatcher) {
            flowStatus {
                getSimilarTvShowsUseCase.execute(
                    GetSimilarTvShowsUseCase.Params(showId, page)
                )
            }
                .collect(
                    onSuccess = {
                        currentPage = it.page
                        canLoadMoreShows = it.page < it.totalPages

                        _uiState.value = _uiState.value.let { state ->
                            state.copy(
                                shows = state.shows.append(it.toDetailUiModel())
                            )
                        }
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