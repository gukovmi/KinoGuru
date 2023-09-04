package com.shellwoo.kinoguru.feature.movie.search.di

import com.shellwoo.kinoguru.feature.movie.search.ui.MovieSearchFragment
import com.shellwoo.kinoguru.shared.error.di.ErrorComponent
import com.shellwoo.kinoguru.shared.language.di.LanguageDataComponent
import com.shellwoo.kinoguru.shared.movie.di.MovieDataComponent
import dagger.Component

@Component(
    dependencies = [ErrorComponent::class, MovieSearchDeps::class, MovieDataComponent::class, LanguageDataComponent::class],
    modules = [MovieSearchPresentationModule::class]
)
internal interface MovieSearchComponent {

    companion object {

        fun create(movieSearchDeps: MovieSearchDeps): MovieSearchComponent =
            DaggerMovieSearchComponent.builder()
                .errorComponent(ErrorComponent.create(movieSearchDeps.context))
                .movieSearchDeps(movieSearchDeps)
                .movieDataComponent(MovieDataComponent.create(movieSearchDeps.context))
                .languageDataComponent(LanguageDataComponent.create())
                .build()
    }

    fun inject(fragment: MovieSearchFragment)
}