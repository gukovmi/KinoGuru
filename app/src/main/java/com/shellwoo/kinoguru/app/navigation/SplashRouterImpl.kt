package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.login.LoginDestination
import com.shellwoo.kinoguru.feature.main.MainDestination
import com.shellwoo.kinoguru.feature.splash.presentation.SplashRouter
import javax.inject.Inject

class SplashRouterImpl @Inject constructor(
    private val appRouter: AppCiceroneRouter,
) : SplashRouter {

    override fun openLoginScreen() {
        appRouter.newRootScreen(LoginDestination)
    }

    override fun openMainScreen() {
        appRouter.newRootScreen(MainDestination)
    }
}