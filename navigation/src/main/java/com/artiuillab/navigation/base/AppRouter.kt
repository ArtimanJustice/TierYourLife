package com.artiuillab.navigation.base

import com.artiuillab.navigation.Route

interface AppRouter {
    fun launch(route: Route)
    fun restart(route: Route)
    fun goBack()
}
