package com.shellwoo.kinoguru.shared.theme.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
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
class ThemeLocalDataSourceTest {

    private val dataStore: DataStore<Preferences> = mock()
    private val gson: Gson = mock()

    private val dataSource = ThemeLocalDataSource(dataStore, gson)

    private val theme = Theme.LIGHT

    @Test
    fun `set EXPECT set in data store`() = runTest {
        dataSource.set(theme)

        verify(dataStore).edit(any())
    }

    @Test
    fun `get EXPECT theme`() = runTest {
        val themeJson = "themeJson"
        val preferences: Preferences = mock()
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        whenever(preferences[stringPreferencesKey("THEME_KEY")]).thenReturn(themeJson)
        whenever(gson.fromJson(themeJson, Theme::class.java)).thenReturn(theme)

        val actual = dataSource.get()

        assertEquals(theme, actual)
    }
}