package com.artiuillab.features.presentation.entities

import androidx.compose.runtime.Stable

@Stable
data class UiKeyFeature(
    val id: Long,
    val title: String,
    val description: String,
)