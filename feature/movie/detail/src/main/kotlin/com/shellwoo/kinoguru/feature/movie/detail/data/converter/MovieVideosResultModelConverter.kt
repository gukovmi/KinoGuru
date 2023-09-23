package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieVideosResultModel
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import javax.inject.Inject

class MovieVideosResultModelConverter @Inject constructor(
    private val movieVideoModelConverter: MovieVideoModelConverter
) {

    fun toMovieVideos(model: MovieVideosResultModel): List<MovieVideo> =
        model.results
            .map(movieVideoModelConverter::toEntity)
            .toList()
}