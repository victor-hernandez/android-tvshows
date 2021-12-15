package dev.victorhernandez.tvshows.data.utils

import dev.victorhernandez.tvshows.data.model.TvShowListItemApiModel
import dev.victorherndez.test.utils.ModelFactory
import dev.victorherndez.test.utils.nextString
import kotlin.random.Random

object TvShowListItemApiModelFactory: ModelFactory<TvShowListItemApiModel> {

    override fun createOne(): TvShowListItemApiModel =
        TvShowListItemApiModel(
            Random.nextInt(),
            Random.nextString(),
            Random.nextString(),
            Random.nextString(),
            (1..Random.nextInt(3)).map { Random.nextString() },
            Random.nextString(),
            (1..Random.nextInt(5)).map { Random.nextInt() },
            Random.nextString(),
            Random.nextString(),
            Random.nextString(),
            Random.nextDouble(),
            Random.nextDouble(),
            Random.nextInt()
        )

}