package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.login.presentation.LoginRouter
import com.shellwoo.kinoguru.feature.main.MainDestination
import javax.inject.Inject

class LoginRouterImpl @Inject constructor(
    private val appRouter: AppCiceroneRouter,
) : LoginRouter {

    override fun openMainScreen() {
        appRouter.newRootScreen(MainDestination)
    }
}