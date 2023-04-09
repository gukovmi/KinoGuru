package com.shellwoo.kinoguru.feature.search.data.repository

import com.shellwoo.kinoguru.feature.search.data.converter.SearchMovieResultModelConverter
import com.shellwoo.kinoguru.feature.search.data.datasource.SearchMovieResultDataSource
import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieResultModel
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class SearchMovieResultRepositoryImplTest {

    private val dataSource: SearchMovieResultDataSource = mock()
    private val converter: SearchMovieResultModelConverter = mock()

    private val repository = SearchMovieResultRepositoryImpl(dataSource, converter)

    @Test
    fun `get EXPECT search movie result`() = runTest {
        val query = "Batman"
        val model: SearchMovieResultModel = mock()
        val expected: SearchMovieResult = mock()
        whenever(converter.from(model)).thenReturn(expected)
        whenever(dataSource.get(query)).thenReturn(model)

        val actual = repository.get(query)

        assertEquals(actual, expected)
    }
}