package com.shellwoo.kinoguru.app.presentation

import com.github.terrakok.cicerone.ResultListener
import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.core.navigation.ResultKeys.NAV_BAR_VISIBILITY_RESULT_KEY
import com.shellwoo.kinoguru.core.navigation.newRootScreenWithNavBarVisibility
import com.shellwoo.kinoguru.feature.splash.SplashDestination
import javax.inject.Inject

interface MainRouter {

    fun setNavBarVisibilityListener(listener: ResultListener)

    fun openSplashScreen()
}

class MainRouterImpl @Inject constructor(
    private val appRouter: Router,
) : MainRouter {

    override fun setNavBarVisibilityListener(listener: ResultListener) {
        appRouter.setResultListener(NAV_BAR_VISIBILITY_RESULT_KEY, listener)
    }

    override fun openSplashScreen() {
        appRouter.newRootScreenWithNavBarVisibility(SplashDestination, navBarVisibility = false)
    }
}