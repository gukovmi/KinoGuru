package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.shared.theme.di.ThemeDataModule
import dagger.Module

@Module(
    includes = [
        AnimationModule::class,
        NavigationCiceroneModule::class,
        NavigationBindsModule::class,
        ThemeDataModule::class,
    ]
)
interface AppModule