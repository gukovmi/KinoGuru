package com.shellwoo.kinoguru.app.di

import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        AnimationModule::class,
        NavigationCiceroneModule::class,
        NavigationBindsModule::class,
        DataModule::class,
        PresentationModule::class,
    ]
)
interface AppModule