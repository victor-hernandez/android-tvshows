package dev.victorhernandez.tvshows.presentation.utils

import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel
import dev.victorherndez.test.utils.ModelFactory
import kotlin.random.Random

object TvShowPageDomainModelFactory : ModelFactory<TvShowPageDomainModel> {

    override fun createOne(): TvShowPageDomainModel =
        TvShowPageDomainModel(
            Random.nextInt(),
            TvShowListItemDomainModelFactory.createMany(),
            Random.nextInt(),
            Random.nextInt()
        )

}