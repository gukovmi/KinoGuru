package com.shellwoo.kinoguru.feature.movie.detail.di

import com.shellwoo.kinoguru.feature.movie.detail.data.network.MovieDetailsApi
import com.shellwoo.kinoguru.feature.movie.detail.data.repository.MovieDetailsRepositoryImpl
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal abstract class MovieDetailsDataModule {

    companion object {

        @Provides
        fun provideSearchMovieApi(retrofit: Retrofit): MovieDetailsApi =
            retrofit.create(MovieDetailsApi::class.java)
    }

    @Binds
    abstract fun bindMovieDetailsRepository(impl: MovieDetailsRepositoryImpl): MovieDetailsRepository
}