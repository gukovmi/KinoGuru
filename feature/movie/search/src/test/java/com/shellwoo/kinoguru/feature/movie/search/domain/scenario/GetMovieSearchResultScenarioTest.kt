package com.shellwoo.kinoguru.feature.movie.search.domain.scenario

import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearchResult
import com.shellwoo.kinoguru.feature.movie.search.domain.repository.MovieSearchResultRepository
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class GetMovieSearchResultScenarioTest {

    private val movieSearchResultRepository: MovieSearchResultRepository = mock()
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase = mock()

    private val scenario = GetMovieSearchResultScenario(movieSearchResultRepository, getCurrentLanguageUseCase)

    @Test
    fun `invoke EXPECT search movie result`() = runTest {
        val query = "Batman"
        val expected: MovieSearchResult = mock()
        val language = Language.ENGLISH
        whenever(getCurrentLanguageUseCase()).thenReturn(language)
        whenever(movieSearchResultRepository.get(query, language, null)).thenReturn(expected)

        val actual = scenario(query)

        assertEquals(expected, actual)
    }
}