package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.feature.splash.di.SplashFragmentModule
import dagger.Module

@Module(
    includes = [
        SplashFragmentModule::class,
    ]
)
interface FragmentModule