package com.shellwoo.kinoguru.feature.movie.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.coroutines.launchTrying
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import com.shellwoo.kinoguru.feature.movie.detail.domain.scenario.GetMovieDetailsScenario

class MovieDetailsViewModel(
    private val getMovieDetailsScenario: GetMovieDetailsScenario,
    private val router: MovieDetailsRouter,
    private val movieId: Int,
) : ViewModel() {

    private val _state = MutableLiveData<MovieDetailsState>(MovieDetailsState.Initial)
    val state: LiveData<MovieDetailsState> = _state

    private val _loadMovieDetailsErrorEvent = SingleLiveEvent<Unit>()
    val loadMovieDetailsErrorEvent: LiveData<Unit> = _loadMovieDetailsErrorEvent

    fun start() {
        if (_state.value != MovieDetailsState.Initial) return

        loadMovieDetails()
    }

    fun loadMovieDetails() {
        _state.value = MovieDetailsState.Loading

        viewModelScope.launchTrying(
            errorHandler = {
                _state.value = MovieDetailsState.Initial
                _loadMovieDetailsErrorEvent(Unit)
            },
            block = {
                val movieDetails = getMovieDetailsScenario(movieId)
                _state.value = MovieDetailsState.Content(movieDetails)
            }
        )
    }

    fun close() {
        router.close()
    }
}