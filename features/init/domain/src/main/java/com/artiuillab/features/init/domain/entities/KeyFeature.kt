package com.artiuillab.features.init.domain.entities

import androidx.compose.runtime.Stable


@Stable
data class KeyFeature(
    val id: Long,
    val title: String,
    val description: String,
)