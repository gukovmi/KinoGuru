package com.shellwoo.kinoguru.feature.movie.detail.data.repository

import com.shellwoo.kinoguru.feature.movie.detail.data.converter.MovieDetailsModelConverter
import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieDetailsModel
import com.shellwoo.kinoguru.feature.movie.detail.data.network.MovieDetailsApi
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
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
class MovieDetailsRepositoryImplTest {

    private val api: MovieDetailsApi = mock()
    private val movieDetailsModelConverter: MovieDetailsModelConverter = mock()
    private val languageTagConverter: LanguageTagConverter = mock()

    private val repository = MovieDetailsRepositoryImpl(api, movieDetailsModelConverter, languageTagConverter)

    @Test
    fun `get EXPECT movie details`() = runTest {
        val language = Language.ENGLISH
        val languageTag = "en"
        val id = 123
        val movieDetailsModel: MovieDetailsModel = mock()
        val expected: MovieDetails = mock()
        whenever(languageTagConverter.toTag(language)).thenReturn(languageTag)
        whenever(api.get(id, languageTag)).thenReturn(movieDetailsModel)
        whenever(movieDetailsModelConverter.toEntity(movieDetailsModel)).thenReturn(expected)

        val actual = repository.get(id, language)

        assertEquals(expected, actual)
    }
}