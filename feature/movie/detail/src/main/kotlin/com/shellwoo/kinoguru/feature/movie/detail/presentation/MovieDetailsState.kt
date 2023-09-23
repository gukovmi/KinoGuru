package com.shellwoo.kinoguru.feature.movie.detail.presentation

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails

sealed interface MovieDetailsState {

    object Initial : MovieDetailsState

    object Loading : MovieDetailsState

    data class Content(val movieDetails: MovieDetails, val movieVideoItems: List<MovieVideoItem>) : MovieDetailsState
}