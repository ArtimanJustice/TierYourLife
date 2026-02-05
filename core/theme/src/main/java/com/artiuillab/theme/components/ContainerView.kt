package com.artiuillab.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.artiuillab.essentials.exceptions.ConnectionException
import com.artiuillab.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.artiuillab.theme.Dimens
import com.artiuillab.theme.MediumVerticalSpace
import com.artiuillab.theme.R
import com.elveum.container.Container
import com.elveum.container.ContainerMapperScope
import com.elveum.container.errorContainer
import com.elveum.container.pendingContainer
import com.elveum.container.successContainer

@Composable
fun <T> ContainerView(
    container: Container<T>,
    modifier: Modifier = Modifier,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper,
    content: @Composable BoxAndMapperScope.(T) -> Unit,
) {
    Box(modifier) {
        container.fold(
            onPending = {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            },
            onError = { exception ->
                val message = exceptionToMessageMapper.getLocalizedMessage(exception)
                ErrorContainerView(
                    message = message,
                    onTryAgain = { reload(silently = false) }
                )
            },
            onSuccess = { value ->
                val combinedScope = BoxAndMapperScopeImpl(this@Box, this)
                combinedScope.content(value)
            }
        )
    }
}

@Composable
private fun BoxScope.ErrorContainerView(
    message: String,
    onTryAgain: () -> Unit,
) {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
        )
        MediumVerticalSpace()
        Button(
            onClick = onTryAgain,
        ) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}

interface BoxAndMapperScope : BoxScope, ContainerMapperScope

private class BoxAndMapperScopeImpl(
    boxScope: BoxScope,
    mapperScope: ContainerMapperScope,
) : BoxAndMapperScope, BoxScope by boxScope, ContainerMapperScope by mapperScope

@Preview(showBackground = true)
@Composable
private fun SuccessContainerView() {
    ContainerView(
        container = successContainer("Test test test")
    ) { value ->
        Text(value)
    }
}

@Preview(showBackground = true)
@Composable
private fun PendingContainerView() {
    ContainerView<String>(
        container = pendingContainer()
    ) { value ->
        Text(value)
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorContainerView() {
    ContainerView<String>(
        container = errorContainer(ConnectionException())
    ) { value ->
        Text(value)
    }
}