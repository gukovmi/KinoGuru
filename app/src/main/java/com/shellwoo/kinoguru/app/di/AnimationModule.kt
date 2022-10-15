package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.core.ui.FragmentTransactionAnimator
import com.shellwoo.kinoguru.feature.login.ui.LoginFragment
import com.shellwoo.kinoguru.feature.main.ui.MainFragment
import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment
import com.shellwoo.kinoguru.feature.splash.ui.SplashFragment
import dagger.Module
import dagger.Provides

@Module
class AnimationModule {

    @Provides
    fun provideFragmentTransactionAnimator(): FragmentTransactionAnimator =
        FragmentTransactionAnimator(
            rootFragments = setOf(
                SplashFragment::class,
                LoginFragment::class,
                MainFragment::class,
                ProfileFragment::class,
            )
        )
}