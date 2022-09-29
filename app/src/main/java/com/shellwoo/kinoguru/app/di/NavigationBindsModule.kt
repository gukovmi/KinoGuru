package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.app.navigation.MainRouterImpl
import com.shellwoo.kinoguru.app.navigation.SplashRouterImpl
import com.shellwoo.kinoguru.feature.main.presentation.MainRouter
import com.shellwoo.kinoguru.feature.splash.presentation.SplashRouter
import dagger.Binds
import dagger.Module

@Module
interface NavigationBindsModule {

    @Binds
    fun bindSplashRouter(impl: SplashRouterImpl): SplashRouter

    @Binds
    fun bindMainRouter(impl: MainRouterImpl): MainRouter
}