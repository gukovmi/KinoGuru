package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        AnimationModule::class,
        NavigationCiceroneModule::class,
        NavigationBindsModule::class,
        DataModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppModule