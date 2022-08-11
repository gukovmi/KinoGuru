package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.core.ui.component.BaseFragmentModule
import com.shellwoo.kinoguru.feature.splash.di.SplashFragmentModule
import dagger.Module

@Module(
    includes = [
        BaseFragmentModule::class,
        SplashFragmentModule::class,
    ]
)
interface FragmentModule