package com.shellwoo.kinoguru.feature.movie.detail.di

import android.content.Context
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsRouter

interface MovieDetailsDeps {

    val context: Context
    val movieDetailsRouter: MovieDetailsRouter
}