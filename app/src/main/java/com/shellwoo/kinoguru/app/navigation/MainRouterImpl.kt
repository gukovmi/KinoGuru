package com.shellwoo.kinoguru.app.navigation

import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.core.navigation.di.MainNavigation
import com.shellwoo.kinoguru.feature.main.presentation.MainRouter
import com.shellwoo.kinoguru.feature.profile.ProfileDestination
import com.shellwoo.kinoguru.feature.search.SearchDestination
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    @MainNavigation private val mainRouter: Router,
) : MainRouter {

    override fun openProfileScreen() {
        mainRouter.newRootScreen(ProfileDestination())
    }

    override fun openSearchScreen() {
        mainRouter.newRootScreen(SearchDestination())
    }
}