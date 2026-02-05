package com.artiuillab.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressButton(
    isInProgress: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(modifier) {
        Button(
            modifier = if (isInProgress) Modifier.alpha(0f) else Modifier,
            enabled = !isInProgress,
            onClick = onClick,
        ) {
            Text(text = text)
        }
        if (isInProgress) {
            SmallProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressButtonPreviewWithoutProgress() {
    ProgressButton(
        isInProgress = false,
        text = "Test Text",
        onClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun ProgressButtonPreviewWithProgress() {
    ProgressButton(
        isInProgress = true,
        text = "Test Text",
        onClick = {},
    )
}
