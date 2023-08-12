package com.shellwoo.kinoguru.shared.theme.domain.usecase

import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetAvailableThemesUseCaseTest {

    private val themeRepository: ThemeRepository = mock()

    private val getAvailableThemesUseCase = GetAvailableThemesUseCase(themeRepository)

    @Test
    fun `invoke EXPECT themes without unspecified`() {
        val allThemes = listOf(
            Theme.LIGHT,
            Theme.DARK,
            Theme.AUTO_BATTERY,
            Theme.AUTO_TIME,
            Theme.FOLLOW_SYSTEM,
            Theme.UNSPECIFIED,
        )
        val expected = listOf(
            Theme.LIGHT,
            Theme.DARK,
            Theme.AUTO_BATTERY,
            Theme.AUTO_TIME,
            Theme.FOLLOW_SYSTEM,
        )
        whenever(themeRepository.getAll()).thenReturn(allThemes)

        val actual = getAvailableThemesUseCase()

        assertEquals(expected, actual)
    }
}