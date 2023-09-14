package com.shellwoo.kinoguru.feature.movie.search.di

import android.content.Context
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchRouter
import com.shellwoo.kinoguru.shared.error.domain.usecase.GetBaseExceptionUseCase
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase

interface MovieSearchDeps {

    val getBaseExceptionUseCase: GetBaseExceptionUseCase
    val getCurrentLanguageUseCase: GetCurrentLanguageUseCase
    val movieSearchRouter: MovieSearchRouter
    val baseExceptionMessageConverter: BaseExceptionMessageConverter
    val context: Context
}