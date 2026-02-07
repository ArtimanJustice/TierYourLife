package com.artiuillab.navigation.base

import com.artiuillab.navigation.Route
import com.artiuillab.navigation.dsl.ScreenScope
import kotlin.reflect.KClass

interface ExtendedNavGraphBuilder {

    fun <T : Route> composable(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit,
    )

}

inline fun <reified T : Route> ExtendedNavGraphBuilder.composable(
    noinline content: ScreenScope.(T) -> Unit,
) = composable(T::class, content)
