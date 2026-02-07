package com.artiuillab.navigation.base.impl

import androidx.navigation.NavController
import com.artiuillab.navigation.Route
import com.artiuillab.navigation.base.AppRouter
import com.uandcode.effects.hilt.annotations.HiltEffect

@HiltEffect
class NavComponentAppRouter(
    private val navController: NavController,
) : AppRouter {

    override fun launch(route: Route) {
        navController.navigate(route)
    }

    override fun restart(route: Route) {
        navController.navigate(route) {
            popUpTo(0)
        }
    }

    override fun goBack() {
        navController.navigateUp()
    }

}
