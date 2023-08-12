package com.shellwoo.kinoguru.shared.theme.data.converter

import androidx.appcompat.app.AppCompatDelegate
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.junit.jupiter.MockitoExtension
import java.util.stream.Stream

@ExtendWith(MockitoExtension::class)
class ThemeConverterTest {

    companion object {

        @JvmStatic
        fun provideNightModeToTheme(): Stream<Pair<Int, Theme>> =
            Stream.of(
                AppCompatDelegate.MODE_NIGHT_NO to Theme.LIGHT,
                AppCompatDelegate.MODE_NIGHT_YES to Theme.DARK,
                AppCompatDelegate.MODE_NIGHT_AUTO_TIME to Theme.AUTO_TIME,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM to Theme.FOLLOW_SYSTEM,
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY to Theme.AUTO_BATTERY,
                312234 to Theme.UNSPECIFIED,
            )
    }

    private val converter = ThemeConverter()

    @ParameterizedTest
    @MethodSource("provideNightModeToTheme")
    fun `from night mode EXPECT theme`(nightModeToTheme: Pair<Int, Theme>) {
        val nightMode = nightModeToTheme.first
        val expected = nightModeToTheme.second

        val actual = converter.fromNightMode(nightMode)

        assertEquals(expected, actual)
    }
}