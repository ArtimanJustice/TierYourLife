package com.artiuillab.navigation.routers

import com.artiuillab.features.presentation.InitRouter
import com.artiuillab.navigation.SignInRoute
import com.artiuillab.navigation.base.AppRouter
import javax.inject.Inject

class InitRouterImpl @Inject constructor(
    private val appRouter: AppRouter,
): InitRouter {

    override fun launchSignIn() {
        appRouter.restart(SignInRoute)
    }

}
