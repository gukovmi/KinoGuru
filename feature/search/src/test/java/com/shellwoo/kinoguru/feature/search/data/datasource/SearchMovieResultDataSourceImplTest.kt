package com.shellwoo.kinoguru.feature.search.data.datasource

import com.shellwoo.kinoguru.feature.search.data.model.SearchMovieResultModel
import com.shellwoo.kinoguru.feature.search.data.network.SearchMovieApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class SearchMovieResultDataSourceImplTest {

    private val api: SearchMovieApi = mock()
    private val dataSource = SearchMovieResultDataSource(api)

    @Test
    fun `get EXPECT search movie result`() = runTest {
        val query = "Batman"
        val expected: SearchMovieResultModel = mock()
        whenever(api.get(query)).thenReturn(expected)

        val actual = dataSource.get(query)

        assertEquals(actual, expected)
    }
}