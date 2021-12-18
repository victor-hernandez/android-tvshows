package dev.victorhernandez.tvshows.presentation.ui.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val getSimilarTvShowsUseCase: GetSimilarTvShowsUseCase,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        TvShowDetailsUiState(
            show = TvShowDetailUiModel(1, "name", null, 10.0, "overview")
        )
    )
    val uiState = _uiState.asStateFlow()

    private var nextPage = 1
    private var canLoadMoreShows = true

    fun loadNextSimilarTvShows(showId: Int) {
        if (canLoadMoreShows) {
            viewModelScope.launch(coroutineDispatcher) {
                flowStatus {
                    getSimilarTvShowsUseCase.execute(
                        GetSimilarTvShowsUseCase.Params(showId, nextPage)
                    )
                }
                    .collect(
                        onSuccess = {
                            nextPage += 1
                            canLoadMoreShows = nextPage < it.totalPages

                            _uiState.value = _uiState.value.let { state ->
                                state.copy(
                                    shows = state.shows.append(it.toDetailUiModel())
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
}