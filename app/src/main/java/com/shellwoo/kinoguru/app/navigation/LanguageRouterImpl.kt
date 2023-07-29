package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.language.presentation.LanguageRouter
import com.shellwoo.kinoguru.feature.main.navigation.MainCiceroneRouter
import javax.inject.Inject

class LanguageRouterImpl @Inject constructor(
    private val mainRouter: MainCiceroneRouter,
) : LanguageRouter {

    override fun close() {
        mainRouter.exit()
    }
}