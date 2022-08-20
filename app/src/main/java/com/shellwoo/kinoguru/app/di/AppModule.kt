package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.core.navigation.di.NavigationModule
import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        NavigationModule::class,
        DataModule::class,
    ]
)
interface AppModule