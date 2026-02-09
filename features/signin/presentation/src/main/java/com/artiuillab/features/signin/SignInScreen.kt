package com.artiuillab.features.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.artiuillab.features.signin.SignInViewModel.State
import com.artiuillab.navigation.dsl.ScreenScope
import com.artiuillab.navigation.dsl.ScreenToolbar
import com.artiuillab.theme.components.ContainerView
import com.elveum.container.Container

fun ScreenScope.signInScreen() {
    toolbar = ScreenToolbar.Default(
        title = context.getString(R.string.sign_in)
    )
    content {
        val viewModel: SignInViewModel = hiltViewModel()
        val container: Container<State> by viewModel.stateFlow.collectAsState()
        ContainerView(container) { state ->
            SignInContent(state)
        }
    }
}

@Composable
fun BoxScope.SignInContent(state: State) {
    Text(
        text = "${state.title} Screen",
        modifier = Modifier.align(Alignment.Center),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Preview(showBackground = true)
@Composable
private fun SignInContentPreview() {
    Box(Modifier.fillMaxSize()) {
        SignInContent(State())
    }
}
