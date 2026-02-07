package com.artiuillab.navigation

import com.artiuillab.features.presentation.initScreen
import com.artiuillab.features.signin.signInScreen
import com.artiuillab.navigation.base.ExtendedNavGraphBuilder
import com.artiuillab.navigation.base.composable

fun ExtendedNavGraphBuilder.buildAppNavGraph() {
    composable<InitRoute> { initScreen() }
    composable<SignInRoute> { signInScreen() }
}
