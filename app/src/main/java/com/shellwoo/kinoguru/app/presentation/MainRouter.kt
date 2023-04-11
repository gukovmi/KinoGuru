package com.shellwoo.kinoguru.app.presentation

import com.shellwoo.kinoguru.app.navigation.AppCiceroneRouter
import com.shellwoo.kinoguru.feature.splash.SplashDestination
import javax.inject.Inject

interface MainRouter {

    fun openSplashScreen()
}

class MainRouterImpl @Inject constructor(
    private val appRouter: AppCiceroneRouter,
) : MainRouter {

    override fun openSplashScreen() {
        appRouter.newRootScreen(SplashDestination)
    }
}