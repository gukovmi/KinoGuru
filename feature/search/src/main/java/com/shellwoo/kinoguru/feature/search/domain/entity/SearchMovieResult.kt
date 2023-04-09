package com.shellwoo.kinoguru.feature.search.domain.entity

data class SearchMovieResult(
    var page: Int?,
    var movies: List<SearchMovie>
)