package com.shellwoo.kinoguru.feature.splash.di

import com.shellwoo.kinoguru.feature.splash.ui.SplashFragment
import com.shellwoo.kinoguru.shared.user.di.UserComponent
import dagger.Component

@Component(
    dependencies = [SplashDeps::class, UserComponent::class],
    modules = [SplashPresentationModule::class]
)
interface SplashComponent {

    companion object {

        fun create(splashDeps: SplashDeps): SplashComponent =
            DaggerSplashComponent.builder()
                .splashDeps(splashDeps)
                .userComponent(UserComponent.create())
                .build()
    }

    fun inject(fragment: SplashFragment)
}