package com.shellwoo.kinoguru.feature.movie.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shellwoo.kinoguru.feature.movie.detail.domain.scenario.GetMovieDetailsScenario
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsRouter
import com.shellwoo.kinoguru.feature.movie.detail.presentation.MovieDetailsViewModel
import com.shellwoo.kinoguru.shared.error.domain.usecase.GetBaseExceptionUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MovieDetailsViewModelFactory @AssistedInject constructor(
    private val getBaseExceptionUseCase: GetBaseExceptionUseCase,
    private val getMovieDetailsScenario: GetMovieDetailsScenario,
    private val router: MovieDetailsRouter,
    @Assisted private val movieId: Int,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(getBaseExceptionUseCase, getMovieDetailsScenario, router, movieId) as T
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted movieId: Int): MovieDetailsViewModelFactory
    }
}