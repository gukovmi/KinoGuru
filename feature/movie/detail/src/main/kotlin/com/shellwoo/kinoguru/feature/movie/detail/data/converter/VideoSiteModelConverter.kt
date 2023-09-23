package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.VideoSite
import javax.inject.Inject

class VideoSiteModelConverter @Inject constructor() {

    fun toEntity(from: String): VideoSite =
        when (from) {
            "YouTube" -> VideoSite.YOU_TUBE
            else -> VideoSite.UNKNOWN
        }
}