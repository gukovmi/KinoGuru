package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.core.ktx.replaceFromEmptyToNull
import com.shellwoo.kinoguru.core.ktx.replaceFromZeroToNull
import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieDetailsModel
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import javax.inject.Inject

class MovieDetailsModelConverter @Inject constructor() {

    fun toEntity(model: MovieDetailsModel): MovieDetails =
        MovieDetails(
            backdropPath = model.backdropPath,
            budget = model.budget.replaceFromZeroToNull(),
            genres = model.genres,
            id = model.id,
            overview = model.overview.replaceFromEmptyToNull(),
            posterPath = model.posterPath,
            productionCompanies = model.productionCompanies,
            releaseDate = model.releaseDate.replaceFromEmptyToNull(),
            revenue = model.revenue.replaceFromZeroToNull(),
            title = model.title.replaceFromEmptyToNull(),
            voteAverage = model.voteAverage.replaceFromZeroToNull(),
        )
}