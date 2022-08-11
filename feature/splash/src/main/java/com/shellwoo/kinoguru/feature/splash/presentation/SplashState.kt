package com.shellwoo.kinoguru.feature.splash.presentation

sealed interface SplashState {

    object Initial : SplashState
    object Content : SplashState
}