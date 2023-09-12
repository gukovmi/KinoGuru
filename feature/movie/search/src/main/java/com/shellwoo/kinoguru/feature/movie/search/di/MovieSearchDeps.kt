package com.shellwoo.kinoguru.feature.movie.search.di

import android.content.Context
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchRouter
import com.shellwoo.kinoguru.shared.error.domain.usecase.GetBaseExceptionUseCase
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter

interface MovieSearchDeps {

    val context: Context
    val getBaseExceptionUseCase: GetBaseExceptionUseCase
    val movieSearchRouter: MovieSearchRouter
    val baseExceptionMessageConverter: BaseExceptionMessageConverter
}