package com.shellwoo.kinoguru.feature.splash.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.FragmentScope
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.splash.presentation.SplashViewModel
import com.shellwoo.kinoguru.feature.splash.ui.SplashFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
private interface SplashPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}

@Module
interface SplashFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [SplashPresentationModule::class])
    fun injectSplashFragment(): SplashFragment
}