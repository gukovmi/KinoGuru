package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.app.navigation.*
import com.shellwoo.kinoguru.feature.language.presentation.LanguageRouter
import com.shellwoo.kinoguru.feature.login.presentation.LoginRouter
import com.shellwoo.kinoguru.feature.main.presentation.MainRouter
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileRouter
import com.shellwoo.kinoguru.feature.splash.presentation.SplashRouter
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeRouter
import dagger.Binds
import dagger.Module

@Module
interface NavigationBindsModule {

    @Binds
    fun bindSplashRouter(impl: SplashRouterImpl): SplashRouter

    @Binds
    fun bindLanguageRouter(impl: LanguageRouterImpl): LanguageRouter

    @Binds
    fun bindLoginRouter(impl: LoginRouterImpl): LoginRouter

    @Binds
    fun bindMainRouter(impl: MainRouterImpl): MainRouter

    @Binds
    fun bindProfileRouter(impl: ProfileRouterImpl): ProfileRouter

    @Binds
    fun bindThemeRouter(impl: ThemeRouterImpl): ThemeRouter
}