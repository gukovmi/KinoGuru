package com.shellwoo.kinoguru.feature.search.data.converter

import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieResultModel
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import javax.inject.Inject

class SearchMovieResultModelConverter @Inject constructor(
    private val searchMovieModelConverter: SearchMovieModelConverter,
) {

    fun from(model: SearchMovieResultModel): SearchMovieResult =
        SearchMovieResult(
            page = model.page,
            movies = model.movies.map(searchMovieModelConverter::from),
        )
}