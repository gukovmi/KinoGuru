package com.shellwoo.kinoguru.feature.movie.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.coroutines.launchTrying
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import com.shellwoo.kinoguru.feature.movie.detail.domain.scenario.GetMovieDetailsScenario
import com.shellwoo.kinoguru.feature.movie.detail.domain.scenario.GetMovieVideosScenario
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.usecase.GetBaseExceptionUseCase

class MovieDetailsViewModel(
    private val getBaseExceptionUseCase: GetBaseExceptionUseCase,
    private val getMovieDetailsScenario: GetMovieDetailsScenario,
    private val getMovieVideosScenario: GetMovieVideosScenario,
    private val movieVideoItemConverter: MovieVideoItemConverter,
    private val router: MovieDetailsRouter,
    private val movieId: Int,
) : ViewModel() {

    private val _state = MutableLiveData<MovieDetailsState>(MovieDetailsState.Initial)
    val state: LiveData<MovieDetailsState> = _state

    private val _loadMovieDetailsErrorEvent = SingleLiveEvent<BaseException>()
    val loadMovieDetailsErrorEvent: LiveData<BaseException> = _loadMovieDetailsErrorEvent

    fun start() {
        if (_state.value != MovieDetailsState.Initial) return

        loadMovieDetails()
    }

    fun loadMovieDetails() {
        _state.value = MovieDetailsState.Loading

        viewModelScope.launchTrying(
            errorHandler = ::handleMovieDetailsLoadingError,
            block = {
                val movieDetails = getMovieDetailsScenario(movieId)
                val movieVideos = getMovieVideosScenario(movieId)
                val movieVideoItems = movieVideoItemConverter.fromEntities(movieVideos)
                _state.value = MovieDetailsState.Content(movieDetails, movieVideoItems)
            }
        )
    }

    private fun handleMovieDetailsLoadingError(exception: Exception) {
        _state.value = MovieDetailsState.Initial
        val baseException = getBaseExceptionUseCase(exception)
        _loadMovieDetailsErrorEvent(baseException)
    }

    fun close() {
        router.close()
    }
}