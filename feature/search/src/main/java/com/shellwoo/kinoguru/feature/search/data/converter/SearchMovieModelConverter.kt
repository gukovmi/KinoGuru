package com.shellwoo.kinoguru.feature.search.data.converter

import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieModel
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovie
import javax.inject.Inject

class SearchMovieModelConverter @Inject constructor() {

    fun from(model: SearchMovieModel): SearchMovie =
        SearchMovie(
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
            popularity = model.popularity,
            voteCount = model.voteCount,
            video = model.video,
            voteAverage = model.voteAverage,
        )
}