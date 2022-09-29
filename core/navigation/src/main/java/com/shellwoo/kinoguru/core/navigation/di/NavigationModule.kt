package com.shellwoo.kinoguru.core.navigation.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.shellwoo.kinoguru.core.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @AppScope
    @AppNavigation
    @Provides
    fun provideAppCicerone(): Cicerone<Router> =
        Cicerone.create()

    @AppNavigation
    @Provides
    fun provideAppNavigatorHolder(@AppNavigation cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @AppNavigation
    @Provides
    fun provideAppRouter(@AppNavigation cicerone: Cicerone<Router>): Router =
        cicerone.router

    @AppScope
    @MainNavigation
    @Provides
    fun provideMainCicerone(): Cicerone<Router> =
        Cicerone.create()

    @MainNavigation
    @Provides
    fun provideMainNavigatorHolder(@MainNavigation cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @MainNavigation
    @Provides
    fun provideMainRouter(@MainNavigation cicerone: Cicerone<Router>): Router =
        cicerone.router
}