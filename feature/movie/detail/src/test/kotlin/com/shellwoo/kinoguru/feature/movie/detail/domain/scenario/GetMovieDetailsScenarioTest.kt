package com.shellwoo.kinoguru.feature.movie.detail.domain.scenario

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieDetailsRepository
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
class GetMovieDetailsScenarioTest {

    private val movieDetailsRepository: MovieDetailsRepository = mock()
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase = mock()

    private val scenario = GetMovieDetailsScenario(movieDetailsRepository, getCurrentLanguageUseCase)

    @Test
    fun `invoke EXPECT movie details`() = runTest {
        val id = 123
        val language = Language.ENGLISH
        val expected: MovieDetails = mock()
        whenever(getCurrentLanguageUseCase()).thenReturn(language)
        whenever(movieDetailsRepository.get(id, language)).thenReturn(expected)

        val actual = scenario(id)

        assertEquals(expected, actual)
    }
}