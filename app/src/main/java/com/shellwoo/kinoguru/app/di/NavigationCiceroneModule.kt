package com.shellwoo.kinoguru.app.di

import com.github.terrakok.cicerone.Cicerone
import com.shellwoo.kinoguru.app.navigation.AppCiceroneRouter
import com.shellwoo.kinoguru.app.navigation.AppNavigatorHolder
import com.shellwoo.kinoguru.feature.main.navigation.MainCiceroneRouter
import com.shellwoo.kinoguru.feature.main.navigation.MainNavigatorHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationCiceroneModule {

    @Singleton
    @Provides
    fun provideAppCiceroneRouter(): Cicerone<AppCiceroneRouter> =
        Cicerone.create(AppCiceroneRouter())

    @Provides
    fun provideAppNavigatorHolder(cicerone: Cicerone<AppCiceroneRouter>): AppNavigatorHolder =
        AppNavigatorHolder(cicerone.getNavigatorHolder())

    @Provides
    fun provideAppRouter(cicerone: Cicerone<AppCiceroneRouter>): AppCiceroneRouter =
        cicerone.router

    @Singleton
    @Provides
    fun provideMainCicerone(): Cicerone<MainCiceroneRouter> =
        Cicerone.create(MainCiceroneRouter())

    @Provides
    fun provideMainNavigatorHolder(cicerone: Cicerone<MainCiceroneRouter>): MainNavigatorHolder =
        MainNavigatorHolder(cicerone.getNavigatorHolder())

    @Provides
    fun provideMainRouter(cicerone: Cicerone<MainCiceroneRouter>): MainCiceroneRouter =
        cicerone.router
}