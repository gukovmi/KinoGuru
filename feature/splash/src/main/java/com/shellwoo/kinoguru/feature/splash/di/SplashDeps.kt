package com.shellwoo.kinoguru.feature.splash.di

import com.shellwoo.kinoguru.feature.splash.presentation.SplashRouter
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase

interface SplashDeps {

    val splashRouter: SplashRouter
    val getCurrentUserUseCase: GetCurrentUserUseCase
}