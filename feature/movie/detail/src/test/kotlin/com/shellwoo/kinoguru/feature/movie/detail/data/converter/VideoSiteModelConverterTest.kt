package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.VideoSite
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VideoSiteModelConverterTest {

    private val converter = VideoSiteModelConverter()

    @Test
    fun `to entity, youtube EXPECT youtube video site`() {
        val videoSiteModel = "YouTube"
        val expected = VideoSite.YOU_TUBE

        val actual = converter.toEntity(videoSiteModel)

        assertEquals(expected, actual)
    }

    @Test
    fun `to entity, unknown EXPECT unknown video site`() {
        val videoSiteModel = "???????"
        val expected = VideoSite.UNKNOWN

        val actual = converter.toEntity(videoSiteModel)

        assertEquals(expected, actual)
    }
}