package com.shellwoo.kinoguru.feature.movie.search.di

import com.shellwoo.kinoguru.feature.movie.search.ui.MovieSearchFragment
import dagger.Component

@Component(
    dependencies = [MovieSearchDeps::class],
    modules = [MovieSearchPresentationModule::class]
)
internal interface MovieSearchComponent {

    companion object {

        fun create(movieSearchDeps: MovieSearchDeps): MovieSearchComponent =
            DaggerMovieSearchComponent.builder()
                .movieSearchDeps(movieSearchDeps)
                .build()
    }

    fun inject(fragment: MovieSearchFragment)
}