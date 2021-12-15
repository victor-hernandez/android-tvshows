package dev.victorhernandez.tvshows.data.utils

import dev.victorhernandez.tvshows.data.model.TvShowListItemDataModel
import dev.victorherndez.test.utils.ModelFactory
import dev.victorherndez.test.utils.nextString
import kotlin.random.Random

object TvShowListItemDataModelFactory : ModelFactory<TvShowListItemDataModel> {

    override fun createOne(): TvShowListItemDataModel =
        TvShowListItemDataModel(
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