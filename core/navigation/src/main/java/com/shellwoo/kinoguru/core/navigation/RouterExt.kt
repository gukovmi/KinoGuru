package com.shellwoo.kinoguru.core.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.core.navigation.ResultKeys.NAV_BAR_VISIBILITY_RESULT_KEY

fun Router.navigateToWithNavBarVisibility(screen: FragmentScreen, navBarVisibility: Boolean = true) {
    sendResult(NAV_BAR_VISIBILITY_RESULT_KEY, navBarVisibility)
    navigateTo(screen)
}

fun Router.newRootScreenWithNavBarVisibility(screen: FragmentScreen, navBarVisibility: Boolean = true) {
    sendResult(NAV_BAR_VISIBILITY_RESULT_KEY, navBarVisibility)
    newRootScreen(screen)
}