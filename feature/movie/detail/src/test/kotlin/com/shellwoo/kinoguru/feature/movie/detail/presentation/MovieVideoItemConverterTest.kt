package com.shellwoo.kinoguru.feature.movie.detail.presentation

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.VideoSite
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieVideoItemConverterTest {

    private val converter = MovieVideoItemConverter()

    @Test
    fun `from entities EXPECT movie video items`() {
        val id = "id"
        val key = "key"
        val name = "name"
        val youtubeSite = VideoSite.YOU_TUBE
        val unknownSite = VideoSite.UNKNOWN
        val youtubeEntity = MovieVideo(
            id = id,
            key = key,
            name = name,
            site = youtubeSite,
        )
        val unknownEntity = MovieVideo(
            id = id,
            key = key,
            name = name,
            site = unknownSite,
        )
        val entities = listOf(youtubeEntity, unknownEntity)
        val youtubeMovieVideoItem = MovieVideoItem.YouTube(
            id = id,
            key = key,
            name = name,
        )
        val expected = listOf(youtubeMovieVideoItem)

        val actual = converter.fromEntities(entities)

        assertEquals(expected, actual)
    }
}