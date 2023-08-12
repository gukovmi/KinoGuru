package com.shellwoo.kinoguru.shared.theme.data.datasource

import androidx.appcompat.app.AppCompatDelegate
import com.shellwoo.kinoguru.shared.theme.data.converter.ThemeConverter
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mockStatic
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class ThemeSystemDataSourceTest {

    private val converter: ThemeConverter = mock()

    private val dataSource = ThemeSystemDataSource(converter)

    private val theme = Theme.LIGHT
    private val nightMode = AppCompatDelegate.MODE_NIGHT_NO

    @Test
    fun `get EXPECT theme`() {
        mockStatic(AppCompatDelegate::class.java).use { appCompatDelegateMock ->
            appCompatDelegateMock.`when`<Int> { AppCompatDelegate.getDefaultNightMode() }.thenReturn(nightMode)
            whenever(converter.fromNightMode(nightMode)).thenReturn(theme)

            val actual = dataSource.get()

            assertEquals(theme, actual)
        }
    }

    @Test
    fun `set EXPECT app compat delegate set night mode`() {
        mockStatic(AppCompatDelegate::class.java).use { appCompatDelegateMock ->
            whenever(converter.toNightMode(theme)).thenReturn(nightMode)

            dataSource.set(theme)

            appCompatDelegateMock.verify { AppCompatDelegate.setDefaultNightMode(nightMode) }
        }
    }
}