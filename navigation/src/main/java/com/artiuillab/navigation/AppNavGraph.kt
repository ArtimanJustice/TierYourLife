package com.artiuillab.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.artiuillab.features.presentation.InitScreen
import com.artiuillab.features.signin.SignInScreen

fun NavGraphBuilder.buildAppNavGraph(
    navController: NavController
) {
    composable<InitRoute> {
        InitScreen(
            onLaunchSignInScreen = {
                navController.navigate(SignInRoute) {
                    popUpTo(0)
                }
            },
        )
    }
    composable<SignInRoute> { SignInScreen() }
}