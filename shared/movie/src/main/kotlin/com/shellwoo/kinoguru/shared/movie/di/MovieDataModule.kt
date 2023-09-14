package com.shellwoo.kinoguru.shared.movie.di

import com.shellwoo.kinoguru.shared.error.ErrorInterceptor
import com.shellwoo.kinoguru.shared.movie.BaseUrls
import com.shellwoo.kinoguru.shared.movie.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MovieDataModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    @Provides
    fun provideTheMovieDbOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(errorInterceptor)
            .build()

    @Provides
    fun provideTheMovieDbRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseUrls.THE_MOVIE_DB_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}