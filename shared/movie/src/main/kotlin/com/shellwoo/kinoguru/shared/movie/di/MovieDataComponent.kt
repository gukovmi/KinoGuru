package com.shellwoo.kinoguru.shared.movie.di

import android.content.Context
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Component(dependencies = [MovieDataDeps::class], modules = [MovieDataModule::class])
interface MovieDataComponent {

    companion object {

        fun create(context: Context): MovieDataComponent =
            DaggerMovieDataComponent.builder()
                .movieDataDeps(createMovieDataDeps(context))
                .build()

        private fun createMovieDataDeps(context: Context): MovieDataDeps =
            object : MovieDataDeps {
                override val context = context
            }
    }

    val okHttpClient: OkHttpClient
    val retrofit: Retrofit
}