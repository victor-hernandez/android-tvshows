package dev.victorhernandez.tvshows.data.utils

import dev.victorhernandez.tvshows.data.model.TvShowPageDataModel
import dev.victorherndez.test.utils.ModelFactory
import kotlin.random.Random

object TvShowPageDataModelFactory : ModelFactory<TvShowPageDataModel> {

    override fun createOne(): TvShowPageDataModel =
        TvShowPageDataModel(
            Random.nextInt(),
            TvShowListItemDataModelFactory.createMany(),
            Random.nextInt(),
            Random.nextInt()
        )

}