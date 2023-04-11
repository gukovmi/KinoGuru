package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.main.navigation.MainCiceroneRouter
import com.shellwoo.kinoguru.feature.main.presentation.MainRouter
import com.shellwoo.kinoguru.feature.profile.ProfileDestination
import com.shellwoo.kinoguru.feature.search.SearchDestination
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    private val mainRouter: MainCiceroneRouter,
) : MainRouter {

    override fun openProfileScreen() {
        mainRouter.newRootScreen(ProfileDestination())
    }

    override fun openSearchScreen() {
        mainRouter.newRootScreen(SearchDestination())
    }
}