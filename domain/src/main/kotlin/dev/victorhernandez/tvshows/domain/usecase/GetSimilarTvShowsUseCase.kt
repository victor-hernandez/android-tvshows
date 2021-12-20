package dev.victorhernandez.tvshows.domain.usecase

import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel
import dev.victorhernandez.tvshows.domain.repository.TvShowRepository
import javax.inject.Inject

class GetSimilarTvShowsUseCase @Inject constructor(
    private val repository: TvShowRepository
) : UseCase<GetSimilarTvShowsUseCase.Params, TvShowPageDomainModel>() {

    data class Params(
        val showId: Int,
        val page: Int
    )

    override suspend fun buildResult(params: Params): TvShowPageDomainModel =
        repository.getSimilarShows(params.showId, params.page)

}