package com.shellwoo.kinoguru.feature.movie.search.domain.entity

data class MovieSearchResult(
    var page: Int?,
    var movies: List<MovieSearch>
)