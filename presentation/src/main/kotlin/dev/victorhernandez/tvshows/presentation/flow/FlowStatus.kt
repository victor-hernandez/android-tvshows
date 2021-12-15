package dev.victorhernandez.tvshows.presentation.flow

sealed class FlowStatus<T> {

    data class Success<T>(val value: T): FlowStatus<T>()

    data class Error<T>(val throwable: Throwable): FlowStatus<T>()

    data class Loading<T>(val isLoading: Boolean): FlowStatus<T>()

}