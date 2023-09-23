package com.shellwoo.kinoguru.feature.movie.detail.di

import com.shellwoo.kinoguru.feature.movie.detail.data.network.MovieDetailsApi
import com.shellwoo.kinoguru.feature.movie.detail.data.network.MovieVideosApi
import com.shellwoo.kinoguru.feature.movie.detail.data.repository.MovieDetailsRepositoryImpl
import com.shellwoo.kinoguru.feature.movie.detail.data.repository.MovieVideosRepositoryImpl
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieDetailsRepository
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieVideosRepository
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

        @Provides
        fun provideMovieVideosApi(retrofit: Retrofit): MovieVideosApi =
            retrofit.create(MovieVideosApi::class.java)
    }

    @Binds
    abstract fun bindMovieDetailsRepository(impl: MovieDetailsRepositoryImpl): MovieDetailsRepository

    @Binds
    abstract fun bindMovieVideosRepository(impl: MovieVideosRepositoryImpl): MovieVideosRepository
}