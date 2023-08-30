package com.shellwoo.kinoguru.feature.movie.search.presentation

sealed interface ScreenState {

    object Initial : ScreenState

    data class Content(val query: String, val searchState: SearchState) : ScreenState
}