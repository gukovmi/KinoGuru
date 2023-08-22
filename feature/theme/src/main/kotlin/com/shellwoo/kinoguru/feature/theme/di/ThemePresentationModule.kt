package com.shellwoo.kinoguru.feature.theme.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface ThemePresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ThemeViewModel::class)
    fun bindViewModel(viewModel: ThemeViewModel): ViewModel
}