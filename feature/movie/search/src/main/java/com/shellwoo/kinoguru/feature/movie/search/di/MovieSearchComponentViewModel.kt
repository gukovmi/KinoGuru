package com.shellwoo.kinoguru.feature.movie.search.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class MovieSearchComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: MovieSearchComponent by lazy {
        MovieSearchComponent.create((app as MovieSearchDepsProvider).movieSearchDeps)
    }
}