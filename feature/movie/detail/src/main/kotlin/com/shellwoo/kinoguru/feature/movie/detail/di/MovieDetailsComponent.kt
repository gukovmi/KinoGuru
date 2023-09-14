package com.shellwoo.kinoguru.feature.movie.detail.di

import com.shellwoo.kinoguru.feature.movie.detail.ui.MovieDetailsFragment
import dagger.Component

@Component(
    dependencies = [MovieDetailsDeps::class],
    modules = [MovieDetailsPresentationModule::class]
)
interface MovieDetailsComponent {

    companion object {

        fun create(movieDetailsDeps: MovieDetailsDeps): MovieDetailsComponent =
            DaggerMovieDetailsComponent.builder()
                .movieDetailsDeps(movieDetailsDeps)
                .build()
    }

    fun inject(movieDetailsFragment: MovieDetailsFragment)
}