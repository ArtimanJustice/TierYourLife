package com.artiuillab.navigation.base.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.artiuillab.navigation.Route
import com.artiuillab.navigation.base.ExtendedNavGraphBuilder
import com.artiuillab.navigation.base.ExtendedNavStore
import com.artiuillab.navigation.dsl.ScreenScope
import kotlin.reflect.KClass

class ExtendedNavGraphBuilderImpl(
    private val navGraphBuilder: NavGraphBuilder,
    private val navStore: ExtendedNavStore,
) : ExtendedNavGraphBuilder {

    override fun <T : Route> composable(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit
    ) {
        navStore.registerConfiguration(routeClass, content)
        navGraphBuilder.composable(routeClass) { navEntry ->
            val route = navEntry.toRoute<T>(routeClass)
            navStore.Content(route, navEntry.id)
        }
    }

}
