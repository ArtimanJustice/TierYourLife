package com.artiuillab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artiuillab.android.AndroidExceptionHandler
import com.artiuillab.essentials.logger.Logger
import com.artiuillab.features.presentation.InitScreen
import com.artiuillab.navigation.AppNavHost
import com.artiuillab.ui.theme.TierYourLifeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var exceptionHandler: AndroidExceptionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TierYourLifeTheme {
                AppNavHost(Modifier.fillMaxSize())
                exceptionHandler.ErrorDialog()

            }
        }
    }
}