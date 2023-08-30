package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.core.ui.component.FragmentTransactionAnimator
import com.shellwoo.kinoguru.feature.language.ui.LanguageFragment
import com.shellwoo.kinoguru.feature.login.ui.LoginFragment
import com.shellwoo.kinoguru.feature.main.ui.MainFragment
import com.shellwoo.kinoguru.feature.movie.detail.ui.MovieDetailsFragment
import com.shellwoo.kinoguru.feature.movie.search.ui.MovieSearchFragment
import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment
import com.shellwoo.kinoguru.feature.splash.ui.SplashFragment
import com.shellwoo.kinoguru.feature.theme.ui.ThemeFragment
import dagger.Module
import dagger.Provides

@Module
class AnimationModule {

    @Provides
    fun provideFragmentTransactionAnimator(): FragmentTransactionAnimator =
        FragmentTransactionAnimator(
            rootFragments = setOf(
                LanguageFragment::class,
                LoginFragment::class,
                MainFragment::class,
                ProfileFragment::class,
                SplashFragment::class,
                MovieSearchFragment::class,
                ThemeFragment::class,
            ),
            flowFragments = setOf(
                MovieDetailsFragment::class,
            )
        )
}