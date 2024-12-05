package com.shellwoo.kinoguru.feature.language.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.language.presentation.LanguageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface LanguagePresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(LanguageViewModel::class)
    fun bindViewModel(viewModel: LanguageViewModel): ViewModel
}