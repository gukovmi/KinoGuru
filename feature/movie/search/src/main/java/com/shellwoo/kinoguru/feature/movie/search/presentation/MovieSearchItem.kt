package com.shellwoo.kinoguru.feature.movie.search.presentation

import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearch

sealed class MovieSearchItem {

    object Loading : MovieSearchItem()

    data class Success(val value: MovieSearch) : MovieSearchItem()
}