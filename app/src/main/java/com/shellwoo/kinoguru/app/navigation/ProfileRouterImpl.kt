package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.login.LoginDestination
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileRouter
import javax.inject.Inject

class ProfileRouterImpl @Inject constructor(private val appRouter: AppCiceroneRouter) : ProfileRouter {

    override fun openLoginScreen() {
        appRouter.newRootScreen(LoginDestination)
    }
}