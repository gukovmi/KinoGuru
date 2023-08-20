package com.shellwoo.kinoguru.feature.search.data.repository

import com.shellwoo.kinoguru.feature.search.data.converter.SearchMovieResultModelConverter
import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieResultModel
import com.shellwoo.kinoguru.feature.search.data.network.SearchMovieApi
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
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
internal class SearchMovieResultRepositoryImplTest {

    private val api: SearchMovieApi = mock()
    private val searchMovieResultModelConverter: SearchMovieResultModelConverter = mock()
    private val languageTagConverter: LanguageTagConverter = mock()

    private val repository = SearchMovieResultRepositoryImpl(api, searchMovieResultModelConverter, languageTagConverter)

    @Test
    fun `get EXPECT search movie result`() = runTest {
        val query = "Batman"
        val model: SearchMovieResultModel = mock()
        val expected: SearchMovieResult = mock()
        val language = Language.ENGLISH
        val languageTag = "en"
        whenever(languageTagConverter.toTag(language)).thenReturn(languageTag)
        whenever(api.get(query, languageTag, null)).thenReturn(model)
        whenever(searchMovieResultModelConverter.from(model)).thenReturn(expected)

        val actual = repository.get(query, language, null)

        assertEquals(actual, expected)
    }
}