package com.shellwoo.kinoguru.feature.movie.search.domain.entity

data class MovieSearch(
    var posterPath: String?,
    var adult: Boolean?,
    var overview: String?,
    var releaseDate: String?,
    var genreIds: ArrayList<Int> = arrayListOf(),
    var id: Int,
    var originalTitle: String?,
    var originalLanguage: String?,
    var title: String?,
    var backdropPath: String?,
    var popularity: Double?,
    var voteCount: Int?,
    var video: Boolean?,
    var voteAverage: Double?
)