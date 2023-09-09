package com.shellwoo.kinoguru.feature.movie.search.presentation

sealed interface SearchState {

    object None : SearchState

    data class Items(val value: List<MovieSearchItem>) : SearchState

    object NotFound : SearchState
}