package com.shellwoo.kinoguru.app.di

import dagger.Module

@Module(
    includes = [
        AnimationModule::class,
        NavigationCiceroneModule::class,
        NavigationBindsModule::class,
        SharedModule::class,
    ]
)
interface AppModule