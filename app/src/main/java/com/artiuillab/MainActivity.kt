package com.artiuillab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.artiuillab.essentials.logger.Logger
import com.artiuillab.ui.theme.TierYourLifeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var logger: Logger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Logger.d("Bla BLa BLa")
        logger.d("Sar sar sar")

        setContent {
            TierYourLifeTheme {}
        }
    }
}