package com.shellwoo.kinoguru.feature.movie.search.data.converter

import com.shellwoo.kinoguru.feature.movie.search.data.model.MovieSearchResultModel
import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearchResult
import javax.inject.Inject

class MovieSearchResultModelConverter @Inject constructor(
    private val movieSearchModelConverter: MovieSearchModelConverter,
) {

    fun from(model: MovieSearchResultModel): MovieSearchResult =
        MovieSearchResult(
            page = model.page,
            movies = model.movies.map(movieSearchModelConverter::from),
        )
}