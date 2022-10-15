package com.shellwoo.kinoguru.feature.search.presentation

sealed interface SearchState {

    object Initial : SearchState

    object Content : SearchState
}