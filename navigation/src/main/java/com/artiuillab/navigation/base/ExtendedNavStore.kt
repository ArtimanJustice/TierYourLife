package com.artiuillab.navigation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.artiuillab.navigation.Route
import com.artiuillab.navigation.dsl.ConfiguredScreen
import com.artiuillab.navigation.dsl.ScreenScope
import kotlin.reflect.KClass

interface ExtendedNavStore {

    val screen: ConfiguredScreen

    fun onBackStackChanged(backStack: List<NavBackStackEntry>)

    fun <T : Route> registerConfiguration(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit
    )

    @Composable
    fun <T : Route> Content(route: T, id: String)

}
