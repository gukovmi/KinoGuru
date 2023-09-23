package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieVideoModel
import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieVideosResultModel
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class MovieVideosResultModelConverterTest {

    private val movieVideoModelConverter: MovieVideoModelConverter = mock()

    private val converter = MovieVideosResultModelConverter(movieVideoModelConverter)

    @Test
    fun `to movie videos EXPECT movie videos`() {
        val movieVideoModel: MovieVideoModel = mock()
        val movieVideoModels = arrayListOf(movieVideoModel)
        val movieVideosResultModel: MovieVideosResultModel = mock { on { it.results } doReturn movieVideoModels }
        val movieVideo: MovieVideo = mock()
        val expected = listOf(movieVideo)
        whenever(movieVideoModelConverter.toEntity(movieVideoModel)).thenReturn(movieVideo)

        val actual = converter.toMovieVideos(movieVideosResultModel)

        assertEquals(expected, actual)
    }
}