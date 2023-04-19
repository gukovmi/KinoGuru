package com.shellwoo.kinoguru.feature.search.domain.usecase

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.feature.search.domain.repository.SearchMovieResultRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class GetSearchMovieResultUseCaseTest {

    private val repository: SearchMovieResultRepository = mock()

    private val useCase = GetSearchMovieResultUseCase(repository)

    @Test
    fun `invoke EXPECT search movie result`() = runTest {
        val query = "Batman"
        val expected: SearchMovieResult = mock()
        whenever(repository.get(query, null)).thenReturn(expected)

        val actual = useCase(query)

        Assertions.assertEquals(expected, actual)
    }
}