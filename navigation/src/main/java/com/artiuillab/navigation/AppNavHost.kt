package com.artiuillab.navigation

import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
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

@Stable
class PaddingValuesTwoWayConverter(
    private val layoutDirection: LayoutDirection
) : TwoWayConverter<PaddingValues, AnimationVector4D> {

    override val convertFromVector: (AnimationVector4D) -> PaddingValues = { vector ->
        PaddingValues.Absolute(
            top = vector.v1.dp,
            bottom = vector.v2.dp,
            left = vector.v3.dp,
            right = vector.v4.dp,
        )
    }

    override val convertToVector: (PaddingValues) -> AnimationVector4D = { paddingValues ->
        AnimationVector4D(
            v1 = paddingValues.calculateTopPadding().value,
            v2 = paddingValues.calculateBottomPadding().value,
            v3 = paddingValues.calculateLeftPadding(layoutDirection).value,
            v4 = paddingValues.calculateRightPadding(layoutDirection).value,
        )
    }
}

fun Modifier.padding(
    paddingValuesProvider: () -> PaddingValues,
): Modifier {
    return this.layout { measurable, constraints ->
        val paddingValues = paddingValuesProvider()

        val leftPadding = paddingValues.calculateLeftPadding(layoutDirection)
        val topPadding = paddingValues.calculateTopPadding()
        val rightPadding = paddingValues.calculateRightPadding(layoutDirection)
        val bottomPadding = paddingValues.calculateBottomPadding()

        val roundedLeftPadding = leftPadding.roundToPx()
        val horizontal = roundedLeftPadding + rightPadding.roundToPx()

        val roundedTopPadding = topPadding.roundToPx()
        val vertical = roundedTopPadding + bottomPadding.roundToPx()

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        layout(width, height) { placeable.place(roundedLeftPadding, roundedTopPadding) }
    }
}