package com.shellwoo.kinoguru.feature.search.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.shellwoo.kinoguru.feature.search.BuildConfig
import com.shellwoo.kinoguru.feature.search.data.datasource.SearchOnboardingShowingDataSourceImpl
import com.shellwoo.kinoguru.feature.search.data.datastore.onboardingShowingDataStore
import com.shellwoo.kinoguru.feature.search.data.network.BaseUrls.THE_MOVIE_DB
import com.shellwoo.kinoguru.feature.search.data.network.SearchMovieApi
import com.shellwoo.kinoguru.feature.search.data.repository.SearchMovieResultRepositoryImpl
import com.shellwoo.kinoguru.feature.search.data.repository.SearchOnboardingShowingRepositoryImpl
import com.shellwoo.kinoguru.feature.search.domain.repository.SearchMovieResultRepository
import com.shellwoo.kinoguru.shared.onboarding.data.datasource.OnboardingShowingDataSource
import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
internal abstract class SearchDataModule {

    companion object {

        @Provides
        fun provideOnboardingDataStore(context: Context): DataStore<Preferences> =
            context.onboardingShowingDataStore

        @Provides
        fun provideTheMovieDbRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(THE_MOVIE_DB)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val url = chain.request().url().newBuilder()
                                .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
                                .build()
                            chain.proceed(chain.request().newBuilder().url(url).build())
                        }
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Provides
        fun provideSearchMovieApi(retrofit: Retrofit): SearchMovieApi =
            retrofit.create(SearchMovieApi::class.java)
    }

    @Binds
    abstract fun bindOnboardingShowingDataSource(impl: SearchOnboardingShowingDataSourceImpl): OnboardingShowingDataSource

    @Binds
    abstract fun bindOnboardingShowingRepository(impl: SearchOnboardingShowingRepositoryImpl): OnboardingShowingRepository

    @Binds
    abstract fun bindSearchMovieResultRepository(impl: SearchMovieResultRepositoryImpl): SearchMovieResultRepository
}