package com.shellwoo.kinoguru.app.navigation

import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.core.navigation.di.AppNavigation
import com.shellwoo.kinoguru.feature.login.presentation.LoginRouter
import com.shellwoo.kinoguru.feature.main.MainDestination
import javax.inject.Inject

class LoginRouterImpl @Inject constructor(
    @AppNavigation private val appRouter: Router,
) : LoginRouter {

    override fun openMainScreen() {
        appRouter.newRootScreen(MainDestination)
    }
}