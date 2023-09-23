package com.shellwoo.kinoguru.feature.movie.detail.domain.scenario

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieVideosRepository
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
class GetMovieVideosScenarioTest {

    private val movieVideosRepository: MovieVideosRepository = mock()
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase = mock()

    private val scenario = GetMovieVideosScenario(movieVideosRepository, getCurrentLanguageUseCase)

    @Test
    fun `invoke EXPECT movie videos`() = runTest {
        val id = 123
        val language = Language.ENGLISH
        val movieVideo: MovieVideo = mock()
        val expected = listOf(movieVideo)
        whenever(getCurrentLanguageUseCase()).thenReturn(language)
        whenever(movieVideosRepository.getAll(id, language)).thenReturn(expected)

        val actual = scenario(id)

        assertEquals(expected, actual)
    }
}