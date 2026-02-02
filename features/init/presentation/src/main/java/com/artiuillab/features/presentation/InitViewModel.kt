package com.artiuillab.features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artiuillab.essentials.Container
import com.artiuillab.essentials.exceptions.base.AbstractAppException
import com.artiuillab.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.artiuillab.features.init.domain.entities.KeyFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.artiuillab.essentials.containerMap

interface GetKeyFeatureUseCase {
     operator fun invoke(): Flow<Container<KeyFeature>>
}

@HiltViewModel
class InitViewModel @Inject constructor(
    private val getKeyFeatureUseCase: GetKeyFeatureUseCase,
    private val exceptionToMessageMapper: ExceptionToMessageMapper,
) : ViewModel() {

    val stateFlow: StateFlow<Container<State>> = getKeyFeatureUseCase
        .invoke()
        .containerMap { keyFeature ->
            State(keyFeature)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(1000),
            Container.Loading,
        )
}

data class State(
    val keyFeature: KeyFeature,
)
//fun <T> ViewModel.load(
//    loader: suspend () -> T,
//): StateFlow<Container<T>> {
//    return flow {
//        emit(Container.Loading)
//        try {
//            val data = loader()
//            emit(Container.Success(data))
//        } catch (e: Exception) {
//            emit(Container.Error(e))
//        }
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), Container.Loading)
//}