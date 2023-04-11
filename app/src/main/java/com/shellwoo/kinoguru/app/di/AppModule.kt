package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.core.navigation.di.NavigationModule
import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        AnimationModule::class,
        NavigationModule::class,
        NavigationBindsModule::class,
        DataModule::class,
        PresentationModule::class,
    ]
)
interface AppModule