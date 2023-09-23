package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieVideoModel
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import javax.inject.Inject

class MovieVideoModelConverter @Inject constructor(private val videoSiteModelConverter: VideoSiteModelConverter) {

    fun toEntity(from: MovieVideoModel): MovieVideo =
        MovieVideo(
            id = from.id,
            key = from.key,
            name = from.name,
            site = videoSiteModelConverter.toEntity(from.site),
        )
}