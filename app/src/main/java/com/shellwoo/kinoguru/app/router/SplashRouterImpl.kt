package com.shellwoo.kinoguru.app.router

import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.feature.login.LoginDestination
import com.shellwoo.kinoguru.feature.splash.presentation.SplashRouter
import javax.inject.Inject

class SplashRouterImpl @Inject constructor(
    private val appRouter: Router,
) : SplashRouter {

    override fun openLoginScreen() {
        appRouter.navigateTo(LoginDestination)
    }
}