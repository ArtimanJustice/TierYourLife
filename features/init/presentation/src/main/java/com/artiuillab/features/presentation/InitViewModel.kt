package com.artiuillab.features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artiuillab.essentials.exceptions.handler.ExceptionHandler
import com.artiuillab.features.init.domain.GetKeyFeatureUseCase
import com.artiuillab.features.init.domain.IsAuthorizedUseCase
import com.artiuillab.features.init.domain.KeyFeature
import com.elveum.container.Container
import com.elveum.container.map
import com.elveum.container.pendingContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.invoke

@HiltViewModel
class InitViewModel @Inject constructor(
    getKeyFeatureUseCase: GetKeyFeatureUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val router: InitRouter,
    private val exceptionHandler: ExceptionHandler,
) : ViewModel() {

    private val vmStateFlow = MutableStateFlow(ViewModelState())

    val stateFlow: StateFlow<Container<State>> = combine(
        getKeyFeatureUseCase.invoke(),
        vmStateFlow,
    ) { container, vmState ->
        container.map { keyFeature ->
            State(keyFeature, vmState.isCheckAuthInProgress)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), pendingContainer())

    fun letsGo() {
        viewModelScope.launch {
            try {
                showProgress()
                val isAuthorized = isAuthorizedUseCase.invoke()
                if (isAuthorized) {
                    // todo: launch main screen
                } else {
                    router.launchSignIn()
                }
            } catch (e: Exception) {
                ensureActive()
                hideProgress()
                exceptionHandler.handleException(e)
            }
        }
    }

    private fun showProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = true) }
    }

    private fun hideProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = false) }
    }

    data class State(
        val keyFeature: KeyFeature,
        val isCheckAuthInProgress: Boolean,
    )

    private data class ViewModelState(
        val isCheckAuthInProgress: Boolean = false
    )

}