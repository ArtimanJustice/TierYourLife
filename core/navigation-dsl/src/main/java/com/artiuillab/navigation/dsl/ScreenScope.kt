package com.artiuillab.navigation.dsl

import android.content.Context
import androidx.compose.runtime.Composable

interface ScreenScope : ConfiguredScreen {
    val context: Context

    override var toolbar: ScreenToolbar
    fun content(block: @Composable () -> Unit)
}
