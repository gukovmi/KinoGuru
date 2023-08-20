package com.shellwoo.kinoguru.feature.search.domain.usecase

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.feature.search.domain.repository.SearchMovieResultRepository
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
internal class GetSearchMovieResultScenarioTest {

    private val searchMovieResultRepository: SearchMovieResultRepository = mock()
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase = mock()

    private val scenario = GetSearchMovieResultScenario(searchMovieResultRepository, getCurrentLanguageUseCase)

    @Test
    fun `invoke EXPECT search movie result`() = runTest {
        val query = "Batman"
        val expected: SearchMovieResult = mock()
        val language = Language.ENGLISH
        whenever(getCurrentLanguageUseCase()).thenReturn(language)
        whenever(searchMovieResultRepository.get(query, language, null)).thenReturn(expected)

        val actual = scenario(query)

        assertEquals(expected, actual)
    }
}