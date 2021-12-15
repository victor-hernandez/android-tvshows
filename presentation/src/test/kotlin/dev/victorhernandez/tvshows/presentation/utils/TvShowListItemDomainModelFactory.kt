package dev.victorhernandez.tvshows.presentation.utils

import dev.victorhernandez.tvshows.domain.model.TvShowListItemDomainModel
import dev.victorherndez.test.utils.ModelFactory
import dev.victorherndez.test.utils.nextString
import kotlin.random.Random

object TvShowListItemDomainModelFactory : ModelFactory<TvShowListItemDomainModel> {

    override fun createOne(): TvShowListItemDomainModel =
        TvShowListItemDomainModel(
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