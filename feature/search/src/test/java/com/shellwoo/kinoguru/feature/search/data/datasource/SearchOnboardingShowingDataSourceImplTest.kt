package com.shellwoo.kinoguru.feature.search.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class SearchOnboardingShowingDataSourceImplTest {

    private val dataStore: DataStore<Preferences> = mock()

    private val dataSource = SearchOnboardingShowingDataSourceImpl(dataStore)

    @Test
    fun `is showed, data store have value EXPECT flow with is showed value`() = runTest {
        val expected = true
        val preferences: Preferences = mock()
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        whenever(preferences[booleanPreferencesKey("SEARCH_MOVIE_ONBOARDING_SHOWED")]).thenReturn(expected)

        val actual = dataSource.isShowed().first()

        assertEquals(expected, actual)
    }

    @Test
    fun `is showed, data store does not have value EXPECT flow with false value`() = runTest {
        val expected = false
        val preferences: Preferences = mock()
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        whenever(preferences[booleanPreferencesKey("SEARCH_MOVIE_ONBOARDING_SHOWED")]).thenReturn(null)

        val actual = dataSource.isShowed().first()

        assertEquals(expected, actual)
    }

    @Test
    fun `set showed EXPECT data store edit`() = runTest {
        dataSource.setShowed()

        verify(dataStore).edit(any())
    }
}