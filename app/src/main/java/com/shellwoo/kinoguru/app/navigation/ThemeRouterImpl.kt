package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.main.navigation.MainCiceroneRouter
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeRouter
import javax.inject.Inject

class ThemeRouterImpl @Inject constructor(
    private val mainRouter: MainCiceroneRouter,
) : ThemeRouter {

    override fun close() {
        mainRouter.exit()
    }
}