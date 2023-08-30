package com.shellwoo.kinoguru.feature.movie.detail.domain.entity

import com.shellwoo.kinoguru.feature.movie.detail.data.model.GenreModel
import com.shellwoo.kinoguru.feature.movie.detail.data.model.ProductionCompanyModel

data class MovieDetails(
    val backdropPath: String?,
    val budget: Int?,
    val genres: ArrayList<GenreModel>,
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val productionCompanies: ArrayList<ProductionCompanyModel>,
    val releaseDate: String?,
    val revenue: Int?,
    val title: String?,
    val voteAverage: Double?,
)
