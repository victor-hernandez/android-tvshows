package dev.victorhernandez.tvshows.data.utils

import dev.victorhernandez.tvshows.data.model.TvShowPageApiModel
import dev.victorherndez.test.utils.ModelFactory
import kotlin.random.Random

object TvShowPageApiModelFactory : ModelFactory<TvShowPageApiModel> {

    override fun createOne(): TvShowPageApiModel =
        TvShowPageApiModel(
            Random.nextInt(),
            TvShowListItemApiModelFactory.createMany(),
            Random.nextInt(),
            Random.nextInt()
        )

}