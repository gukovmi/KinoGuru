package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieVideoModel
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.VideoSite
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class MovieVideoModelConverterTest {

    private val videoSiteModelConverter: VideoSiteModelConverter = mock()

    private val converter = MovieVideoModelConverter(videoSiteModelConverter)

    @Test
    fun `to entity EXPECT movie video`() {
        val id = "id"
        val key = "key"
        val name = "name"
        val siteModel = "YouTube"
        val siteEntity = VideoSite.YOU_TUBE
        val movieVideoModel: MovieVideoModel = mock {
            on { it.id } doReturn id
            on { it.key } doReturn key
            on { it.name } doReturn name
            on { it.site } doReturn siteModel
        }
        val expected = MovieVideo(
            id = id,
            key = key,
            name = name,
            site = siteEntity,
        )
        whenever(videoSiteModelConverter.toEntity(siteModel)).thenReturn(siteEntity)

        val actual = converter.toEntity(movieVideoModel)

        assertEquals(expected, actual)
    }
}