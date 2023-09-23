package com.shellwoo.kinoguru.feature.movie.detail.data.repository

import com.shellwoo.kinoguru.feature.movie.detail.data.converter.MovieVideosResultModelConverter
import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieVideosResultModel
import com.shellwoo.kinoguru.feature.movie.detail.data.network.MovieVideosApi
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.shared.language.data.converter.LanguageTagConverter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class MovieVideosRepositoryImplTest {

    private val api: MovieVideosApi = mock()
    private val movieVideosResultModelConverter: MovieVideosResultModelConverter = mock()
    private val languageTagConverter: LanguageTagConverter = mock()

    private val repository = MovieVideosRepositoryImpl(api, movieVideosResultModelConverter, languageTagConverter)

    @Test
    fun `get all EXPECT movie videos`() = runTest {
        val language = Language.ENGLISH
        val languageTag = "en"
        val id = 123
        val movieVideosResultModel: MovieVideosResultModel = mock()
        val movieVideo: MovieVideo = mock()
        val expected = listOf(movieVideo)
        whenever(languageTagConverter.toTag(language)).thenReturn(languageTag)
        whenever(api.getAll(id, languageTag)).thenReturn(movieVideosResultModel)
        whenever(movieVideosResultModelConverter.toMovieVideos(movieVideosResultModel)).thenReturn(expected)

        val actual = repository.getAll(id, language)

        assertEquals(expected, actual)
    }
}