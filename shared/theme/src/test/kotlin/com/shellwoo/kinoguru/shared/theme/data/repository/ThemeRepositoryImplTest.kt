package com.shellwoo.kinoguru.shared.theme.data.repository

import com.shellwoo.kinoguru.shared.theme.data.datasource.ThemeLocalDataSource
import com.shellwoo.kinoguru.shared.theme.data.datasource.ThemeSystemDataSource
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class ThemeRepositoryImplTest {

    private val systemDataSource: ThemeSystemDataSource = mock()
    private val localDataSource: ThemeLocalDataSource = mock()

    private val repository = ThemeRepositoryImpl(systemDataSource, localDataSource)

    private val theme = Theme.LIGHT

    @Test
    fun `init, local theme is null EXPECT local data source set theme`() = runTest {
        whenever(localDataSource.get()).thenReturn(null)
        whenever(systemDataSource.get()).thenReturn(theme)

        repository.init()

        verify(localDataSource).set(theme)
    }

    @Test
    fun `init, local theme is not null EXPECT system data source set theme`() = runTest {
        whenever(localDataSource.get()).thenReturn(theme)

        repository.init()

        verify(systemDataSource).set(theme)
    }

    @Test
    fun `get EXPECT theme`() = runTest {
        whenever(systemDataSource.get()).thenReturn(theme)

        val actual = repository.get()

        assertEquals(theme, actual)
    }

    @Test
    fun `get all EXPECT themes list`() = runTest {
        val expected = Theme.values().toList()

        val actual = repository.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `set EXPECT local data source set`() = runTest {
        repository.set(theme)

        verify(localDataSource).set(theme)
    }

    @Test
    fun `set EXPECT system data source set`() = runTest {
        repository.set(theme)

        verify(systemDataSource).set(theme)
    }
}