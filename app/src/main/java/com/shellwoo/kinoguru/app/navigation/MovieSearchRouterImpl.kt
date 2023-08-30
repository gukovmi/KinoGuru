package com.shellwoo.kinoguru.app.navigation

import com.shellwoo.kinoguru.feature.main.navigation.MainCiceroneRouter
import com.shellwoo.kinoguru.feature.movie.detail.MovieDetailsDestination
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchRouter
import javax.inject.Inject

class MovieSearchRouterImpl @Inject constructor(private val mainRouter: MainCiceroneRouter) : MovieSearchRouter {

    override fun openMovieDetailsScreen(movieId: Int) {
        mainRouter.navigateTo(MovieDetailsDestination(movieId))
    }
}