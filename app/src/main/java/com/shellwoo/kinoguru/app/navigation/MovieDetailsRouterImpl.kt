package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.main.navigation.MainCiceroneRouter
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsRouter
import javax.inject.Inject

class MovieDetailsRouterImpl @Inject constructor(private val mainRouter: MainCiceroneRouter) : MovieDetailsRouter {

    override fun close() {
        mainRouter.exit()
    }
}