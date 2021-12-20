package dev.victorhernandez.tvshows.presentation.ktx

internal fun <T> List<T>?.append(items: Collection<T>?): List<T> =
    (this?.toMutableList() ?: mutableListOf()).apply { addAll(items ?: listOf()) }