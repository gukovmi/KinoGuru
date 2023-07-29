package com.shellwoo.kinoguru.feature.language.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.shared.language.di.LanguageDataModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [LanguageDataModule::class, ViewModelFactoryModule::class])
interface LanguagePresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(com.shellwoo.kinoguru.feature.language.presentation.LanguageViewModel::class)
    fun bindViewModel(viewModel: com.shellwoo.kinoguru.feature.language.presentation.LanguageViewModel): ViewModel
}