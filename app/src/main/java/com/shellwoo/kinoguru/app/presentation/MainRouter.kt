package com.shellwoo.kinoguru.app.presentation

import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.core.navigation.di.AppNavigation
import com.shellwoo.kinoguru.feature.splash.SplashDestination
import javax.inject.Inject

interface MainRouter {

    fun openSplashScreen()
}

class MainRouterImpl @Inject constructor(
    @AppNavigation private val appRouter: Router,
) : MainRouter {

    override fun openSplashScreen() {
        appRouter.newRootScreen(SplashDestination)
    }
}