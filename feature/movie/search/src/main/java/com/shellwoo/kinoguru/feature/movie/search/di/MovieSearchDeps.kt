package com.shellwoo.kinoguru.feature.movie.search.di

import android.content.Context
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchRouter

interface MovieSearchDeps {

    val context: Context
    val movieSearchRouter: MovieSearchRouter
}