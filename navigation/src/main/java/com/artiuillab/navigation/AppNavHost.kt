package com.artiuillab.navigation

import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.artiuillab.navigation.animation.PaddingValuesTwoWayConverter
import com.artiuillab.navigation.animation.padding
import com.artiuillab.navigation.base.ExtendedNavGraphBuilder
import com.artiuillab.navigation.base.impl.ExtendedNavGraphBuilderImpl
import com.artiuillab.navigation.base.impl.ExtendedNavStoreImpl
import com.artiuillab.navigation.base.impl.NavComponentAppRouter
import com.artiuillab.navigation.dsl.ScreenToolbar
import com.uandcode.effects.hilt.compose.EffectProvider


@Composable
fun AppNavHost(
    modifier: Modifier,
    startDestination: Route = InitRoute,
    navGraphBuilder: ExtendedNavGraphBuilder.() -> Unit = {},
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val navStore = remember { ExtendedNavStoreImpl(context) }
    val navGraph = remember {
        navController.createGraph(startDestination) {
            with(ExtendedNavGraphBuilderImpl(this, navStore)) {
                buildAppNavGraph()
                navGraphBuilder()
            }
        }
    }
    var showBackButton by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        val navigator = navController.navigatorProvider
            .getNavigator(ComposeNavigator::class.java)
        navigator.backStack.collect { backStack ->
            navStore.onBackStackChanged(backStack)
            showBackButton = backStack.size > 1
        }
    }
    val router = remember { NavComponentAppRouter(navController) }
    EffectProvider(router) {
        Scaffold(
            modifier = modifier,
            topBar = {
                val toolbar = navStore.screen.toolbar
                if (toolbar is ScreenToolbar.Default) {
                    AppToolbar(
                        title = toolbar.title,
                        showBackButton = showBackButton,
                        onBackPressed = { navController.navigateUp() }
                    )
                }
            }
        ) { paddingValues ->
            val layoutDirection = LocalLayoutDirection.current
            val paddingValuesTwoWayConverter = remember(layoutDirection) {
                PaddingValuesTwoWayConverter(layoutDirection)
            }

            val animatedPaddingValues: PaddingValues by animateValueAsState(
                targetValue = paddingValues,
                animationSpec = tween(700),
                typeConverter = paddingValuesTwoWayConverter
            )

            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding { animatedPaddingValues },
                navController = navController,
                graph = navGraph,
            )
        }
    }
}