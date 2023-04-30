package com.shellwoo.kinoguru.feature.search.presentation

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovie

sealed class SearchMovieItem {

    object Loading : SearchMovieItem()

    data class Success(val value: SearchMovie) : SearchMovieItem()
}