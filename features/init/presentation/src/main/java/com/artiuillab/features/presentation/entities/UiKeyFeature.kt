package com.artiuillab.features.presentation.entities

import androidx.compose.runtime.Stable
import com.artiuillab.features.init.domain.entities.KeyFeature

@Stable
data class UiKeyFeature(
    val id: Long,
    val title: String,
    val description: String,
)

fun KeyFeature.toUiKeyFeature(): UiKeyFeature {
    return UiKeyFeature(
        id = this.id,
        title = this.title,
        description = this.description,
    )
}