package com.shellwoo.kinoguru.feature.search.presentation

sealed interface SearchState {

    object None : SearchState

    data class Result(val items: List<SearchMovieItem>) : SearchState
}