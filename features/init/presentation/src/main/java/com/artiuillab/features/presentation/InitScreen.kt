package com.artiuillab.features.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.artiuillab.essentials.Container

@Composable
fun InitScreen() {
    val viewModel: InitViewModel = hiltViewModel()
    val container: Container<State> by viewModel.stateFlow.collectAsState()


    InitContent("qwe")
}


@Composable
fun InitContent(text: String) {
    Text("")
}