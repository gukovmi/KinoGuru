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
    @Provides
    fun provideCicerone(): Cicerone<Router> =
        Cicerone.create()

    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router =
        cicerone.router
}