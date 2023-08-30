package com.shellwoo.kinoguru.shared.movie.di

import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Component(modules = [MovieDataModule::class])
interface MovieDataComponent {

    companion object {

        fun create(): MovieDataComponent =
            DaggerMovieDataComponent.create()
    }

    val okHttpClient: OkHttpClient
    val retrofit: Retrofit
}