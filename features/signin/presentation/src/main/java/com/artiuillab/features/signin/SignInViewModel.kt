package com.artiuillab.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elveum.container.Container
import com.elveum.container.pendingContainer
import com.elveum.container.successContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    // todo: inject use-cases
) : ViewModel() {

    // todo: execute use-cases
    val stateFlow: StateFlow<Container<State>> = flowOf(successContainer(State()))
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), pendingContainer())

    // todo: declare screen state
    data class State(
        val title: String = "SignIn"
    )

}
