package dev.victorhernandez.tvshows.presentation.flow

import kotlinx.coroutines.flow.*

fun <T> flowStatus(block: suspend () -> T): Flow<FlowStatus<T>> =
    flow{
       emit(block())
    }.flowStatus()

fun <T> Flow<T>.flowStatus(): Flow<FlowStatus<T>> =
    this.map {
        FlowStatus.Success(it) as FlowStatus<T>
    }.catch {
        emit(FlowStatus.Error(it))
    }.onStart {
        emit(FlowStatus.Loading(true))
    }.onCompletion {
        emit(FlowStatus.Loading(false))
    }

suspend fun <T> Flow<FlowStatus<T>>.collect(
    onSuccess: ((T) -> Unit),
    onError: ((Throwable) -> Unit)? = null,
    onLoading: ((Boolean) -> Unit)? = null
) {
    this.collect {
        when(it) {
            is FlowStatus.Success -> onSuccess.invoke(it.value)
            is FlowStatus.Error -> onError?.invoke(it.throwable)
            is FlowStatus.Loading -> onLoading?.invoke(it.isLoading)
        }
    }
}