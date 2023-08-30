package com.shellwoo.kinoguru.feature.movie.search.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.shellwoo.kinoguru.feature.movie.search.data.datasource.MovieSearchOnboardingShowingDataSourceImpl
import com.shellwoo.kinoguru.feature.movie.search.data.datastore.onboardingShowingDataStore
import com.shellwoo.kinoguru.feature.movie.search.data.network.MovieSearchApi
import com.shellwoo.kinoguru.feature.movie.search.data.repository.MovieSearchOnboardingShowingRepositoryImpl
import com.shellwoo.kinoguru.feature.movie.search.data.repository.MovieSearchResultRepositoryImpl
import com.shellwoo.kinoguru.feature.movie.search.domain.repository.MovieSearchResultRepository
import com.shellwoo.kinoguru.shared.onboarding.data.datasource.OnboardingShowingDataSource
import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal abstract class MovieSearchDataModule {

    companion object {

        @Provides
        fun provideOnboardingDataStore(context: Context): DataStore<Preferences> =
            context.onboardingShowingDataStore

        @Provides
        fun provideSearchMovieApi(retrofit: Retrofit): MovieSearchApi =
            retrofit.create(MovieSearchApi::class.java)
    }

    @Binds
    abstract fun bindOnboardingShowingDataSource(impl: MovieSearchOnboardingShowingDataSourceImpl): OnboardingShowingDataSource

    @Binds
    abstract fun bindOnboardingShowingRepository(impl: MovieSearchOnboardingShowingRepositoryImpl): OnboardingShowingRepository

    @Binds
    abstract fun bindMovieSearchResultRepository(impl: MovieSearchResultRepositoryImpl): MovieSearchResultRepository
}