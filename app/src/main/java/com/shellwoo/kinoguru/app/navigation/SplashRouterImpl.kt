package com.shellwoo.kinoguru.app.navigation


import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.core.navigation.di.AppNavigation
import com.shellwoo.kinoguru.feature.login.LoginDestination
import com.shellwoo.kinoguru.feature.splash.presentation.SplashRouter
import javax.inject.Inject

class SplashRouterImpl @Inject constructor(
    @AppNavigation private val appRouter: Router,
) : SplashRouter {

    override fun openLoginScreen() {
        appRouter.newRootScreen(LoginDestination)
    }
}