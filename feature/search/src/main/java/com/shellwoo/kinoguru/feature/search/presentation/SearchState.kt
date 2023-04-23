package com.shellwoo.kinoguru.feature.search.presentation

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult

sealed interface SearchState {

    object None : SearchState

    object Loading : SearchState

    data class Successful(val result: SearchMovieResult) : SearchState
}