package com.shellwoo.kinoguru.feature.movie.search.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [MovieSearchDataModule::class, ViewModelFactoryModule::class])
internal interface MovieSearchPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieSearchViewModel::class)
    fun bindSearchViewModel(impl: MovieSearchViewModel): ViewModel
}