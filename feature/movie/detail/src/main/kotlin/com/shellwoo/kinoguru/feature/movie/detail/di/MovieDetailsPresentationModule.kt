package com.shellwoo.kinoguru.feature.movie.detail.di

import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.feature.movie.detail.di.MovieDetailsDataModule
import dagger.Module

@Module(includes = [MovieDetailsDataModule::class, ViewModelFactoryModule::class])
interface MovieDetailsPresentationModule