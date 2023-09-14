package com.shellwoo.kinoguru.feature.movie.detail.di

import android.content.Context
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsRouter
import com.shellwoo.kinoguru.shared.error.domain.usecase.GetBaseExceptionUseCase
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import retrofit2.Retrofit

interface MovieDetailsDeps {

    val retrofit: Retrofit
    val getBaseExceptionUseCase: GetBaseExceptionUseCase
    val getCurrentLanguageUseCase: GetCurrentLanguageUseCase
    val movieDetailsRouter: MovieDetailsRouter
    val baseExceptionMessageConverter: BaseExceptionMessageConverter
    val context: Context
}