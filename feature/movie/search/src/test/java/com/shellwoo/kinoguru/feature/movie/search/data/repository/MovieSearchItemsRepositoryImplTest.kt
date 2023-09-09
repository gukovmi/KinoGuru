package com.shellwoo.kinoguru.feature.movie.search.data.repository

import com.shellwoo.kinoguru.feature.movie.search.data.converter.MovieSearchResultModelConverter
import com.shellwoo.kinoguru.feature.movie.search.data.model.MovieSearchResultModel
import com.shellwoo.kinoguru.feature.movie.search.data.network.MovieSearchApi
import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearchResult
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
internal class MovieSearchItemsRepositoryImplTest {

    private val api: MovieSearchApi = mock()
    private val movieSearchResultModelConverter: MovieSearchResultModelConverter = mock()
    private val languageTagConverter: LanguageTagConverter = mock()

    private val repository = MovieSearchResultRepositoryImpl(api, movieSearchResultModelConverter, languageTagConverter)

    @Test
    fun `get EXPECT search movie result`() = runTest {
        val query = "Batman"
        val model: MovieSearchResultModel = mock()
        val expected: MovieSearchResult = mock()
        val language = Language.ENGLISH
        val languageTag = "en"
        whenever(languageTagConverter.toTag(language)).thenReturn(languageTag)
        whenever(api.get(query, languageTag, null)).thenReturn(model)
        whenever(movieSearchResultModelConverter.from(model)).thenReturn(expected)

        val actual = repository.get(query, language, null)

        assertEquals(actual, expected)
    }
}