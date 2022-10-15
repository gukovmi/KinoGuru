package com.shellwoo.kinoguru.app.navigation

import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.core.navigation.di.AppNavigation
import com.shellwoo.kinoguru.feature.login.LoginDestination
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileRouter
import javax.inject.Inject

class ProfileRouterImpl @Inject constructor(@AppNavigation private val appRouter: Router) : ProfileRouter {

    override fun openLoginScreen() {
        appRouter.newRootScreen(LoginDestination)
    }
}