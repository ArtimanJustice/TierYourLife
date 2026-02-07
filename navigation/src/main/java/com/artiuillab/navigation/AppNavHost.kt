package com.artiuillab.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.artiuillab.navigation.base.ExtendedNavGraphBuilder
import com.artiuillab.navigation.base.impl.ExtendedNavGraphBuilderImpl
import com.artiuillab.navigation.base.impl.ExtendedNavStoreImpl
import com.artiuillab.navigation.base.impl.NavComponentAppRouter
import com.uandcode.effects.hilt.compose.EffectProvider
import com.artiuillab.navigation.dsl.ScreenToolbar


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
            // todo #1: optimize animation to skip composition phase
            // todo #2: animate and use all paddings, not only the top one
            val animatedTopPadding = animateDpAsState(
                targetValue = paddingValues.calculateTopPadding(),
            )
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = animatedTopPadding.value),
                navController = navController,
                graph = navGraph,
            )
        }
    }
}
