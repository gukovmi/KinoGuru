package com.shellwoo.kinoguru.feature.search.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.search.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [SearchDataModule::class, ViewModelFactoryModule::class])
internal interface SearchPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(impl: SearchViewModel): ViewModel
}