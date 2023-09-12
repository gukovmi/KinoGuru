package com.shellwoo.kinoguru.feature.splash.di

import com.shellwoo.kinoguru.feature.splash.ui.SplashFragment
import dagger.Component

@Component(
    dependencies = [SplashDeps::class],
    modules = [SplashPresentationModule::class]
)
interface SplashComponent {

    companion object {

        fun create(splashDeps: SplashDeps): SplashComponent =
            DaggerSplashComponent.builder()
                .splashDeps(splashDeps)
                .build()
    }

    fun inject(fragment: SplashFragment)
}