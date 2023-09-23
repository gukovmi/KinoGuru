package com.shellwoo.kinoguru.feature.movie.detail.presentation

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.VideoSite
import javax.inject.Inject

class MovieVideoItemConverter @Inject constructor() {

    fun fromEntities(entities: List<MovieVideo>): List<MovieVideoItem> =
        entities.mapNotNull(::fromEntity)

    private fun fromEntity(entity: MovieVideo): MovieVideoItem? =
        when (entity.site) {
            VideoSite.YOU_TUBE -> MovieVideoItem.YouTube(id = entity.id, key = entity.key, name = entity.name)
            VideoSite.UNKNOWN -> null
        }
}