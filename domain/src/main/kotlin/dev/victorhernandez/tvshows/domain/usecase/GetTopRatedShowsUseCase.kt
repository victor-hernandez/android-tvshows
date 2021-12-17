package dev.victorhernandez.tvshows.domain.usecase

import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel
import dev.victorhernandez.tvshows.domain.repository.TvShowRepository
import javax.inject.Inject

class GetTopRatedShowsUseCase @Inject constructor(
    private val repository: TvShowRepository
): UseCase<GetTopRatedShowsUseCase.Params, TvShowPageDomainModel>() {

    data class Params(
        val page: Int
    )

    override suspend fun buildResult(params: Params): TvShowPageDomainModel =
        repository.getTopRatedShows(params.page)

}