package com.artiuillab.essentials

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class Container<out T> {

    data object Loading : Container<Nothing>()

    data class Error(val exception: Exception) : Container<Nothing>()

    data class Success<T>(val data: T) : Container<T>()
}

fun <T, R> Container<T>.map(transform: (T) -> R): Container<R> {
    return when (this) {
        is Container.Loading -> Container.Loading
        is Container.Error -> Container.Error(this.exception)
        is Container.Success -> Container.Success(transform(this.data))
    }
}

fun <T, R> Flow<Container<T>>.containerMap(
    transform: (T) -> R
): Flow<Container<R>> {
    return this.map { container -> container.map(transform) }
}