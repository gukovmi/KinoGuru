package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.language.LanguageDestination
import com.shellwoo.kinoguru.feature.login.LoginDestination
import com.shellwoo.kinoguru.feature.main.navigation.MainCiceroneRouter
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileRouter
import javax.inject.Inject

class ProfileRouterImpl @Inject constructor(
    private val appRouter: AppCiceroneRouter,
    private val mainRouter: MainCiceroneRouter,
) : ProfileRouter {

    override fun openLoginScreen() {
        appRouter.newRootScreen(LoginDestination)
    }

    override fun openLanguageScreen() {
        mainRouter.navigateTo(LanguageDestination())
    }
}