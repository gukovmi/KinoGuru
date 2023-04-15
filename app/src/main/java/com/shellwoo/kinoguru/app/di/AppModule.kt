package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import dagger.Module

@Module(
    includes = [
        AnimationModule::class,
        NavigationCiceroneModule::class,
        NavigationBindsModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppModule