package com.artiuillab.features.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.artiuillab.features.init.domain.KeyFeature
import com.artiuillab.features.init.presentation.R
import com.artiuillab.features.presentation.InitViewModel.State
import com.artiuillab.theme.Dimens
import com.artiuillab.theme.components.ContainerView
import com.artiuillab.theme.components.ProgressButton
import com.elveum.container.Container
import com.artiuillab.navigation.dsl.ScreenScope
import com.artiuillab.navigation.dsl.ScreenToolbar

fun ScreenScope.initScreen() {
    toolbar = ScreenToolbar.Hidden
    content {
        val viewModel: InitViewModel = hiltViewModel()
        val container: Container<State> by viewModel.stateFlow.collectAsState()
        ContainerView(
            container = container,
            modifier = Modifier.fillMaxSize(),
        ) { state ->
            InitContent(
                state = state,
                onLetsGoAction = viewModel::letsGo,
            )
        }
    }
}

@Composable
fun InitContent(
    state: State,
    onLetsGoAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumSpace, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val keyFeature = state.keyFeature
        Text(
            text = keyFeature.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Text(
            text = keyFeature.description,
            textAlign = TextAlign.Center,
        )
        ProgressButton(
            isInProgress = state.isCheckAuthInProgress,
            text = stringResource(R.string.let_s_go),
            onClick = onLetsGoAction,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InitContentPreview() {
    InitContent(
        State(
            keyFeature = KeyFeature(
                id = 0L,
                title = "Est ut quam qui suscipit quod.",
                description = "Iste pariatur mollitia fugit. " +
                        "Ipsam doloremque quae et officiis aut est delectus. " +
                        "Voluptatem dolorem similique pariatur vitae suscipit voluptatem aspernatur."
            ),
            isCheckAuthInProgress = true,
        ),
        onLetsGoAction = {},
    )
}
