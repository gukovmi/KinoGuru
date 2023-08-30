package com.shellwoo.kinoguru.feature.movie.detail.di

import com.shellwoo.kinoguru.feature.movie.detail.ui.MovieDetailsFragment
import com.shellwoo.kinoguru.shared.language.di.LanguageDataComponent
import com.shellwoo.kinoguru.shared.movie.di.MovieDataComponent
import dagger.Component

@Component(
    dependencies = [MovieDetailsDeps::class, LanguageDataComponent::class, MovieDataComponent::class],
    modules = [MovieDetailsPresentationModule::class]
)
interface MovieDetailsComponent {

    companion object {

        fun create(movieDetailsDeps: MovieDetailsDeps): MovieDetailsComponent =
            DaggerMovieDetailsComponent.builder()
                .movieDetailsDeps(movieDetailsDeps)
                .languageDataComponent(LanguageDataComponent.create())
                .movieDataComponent(MovieDataComponent.create())
                .build()
    }

    fun inject(movieDetailsFragment: MovieDetailsFragment)
}