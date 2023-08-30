package com.shellwoo.kinoguru.feature.movie.detail.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MovieDetailsComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: MovieDetailsComponent by lazy { MovieDetailsComponent.create((app as MovieDetailsDepsProvider).movieDetailsDeps) }
}