package com.shellwoo.kinoguru.feature.movie.search.data.converter

import com.shellwoo.kinoguru.core.ktx.replaceFromZeroToNull
import com.shellwoo.kinoguru.feature.movie.search.data.model.MovieSearchModel
import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearch
import javax.inject.Inject

class MovieSearchModelConverter @Inject constructor() {

    fun from(model: MovieSearchModel): MovieSearch =
        MovieSearch(
            posterPath = model.posterPath,
            adult = model.adult,
            overview = model.overview,
            releaseDate = model.releaseDate,
            genreIds = model.genreIds,
            id = model.id,
            originalTitle = model.originalTitle,
            originalLanguage = model.originalLanguage,
            title = model.title,
            backdropPath = model.backdropPath,
            popularity = model.popularity.replaceFromZeroToNull(),
            voteCount = model.voteCount,
            video = model.video,
            voteAverage = model.voteAverage.replaceFromZeroToNull(),
        )
}