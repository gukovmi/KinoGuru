package com.shellwoo.kinoguru.feature.theme.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.usecase.GetAvailableThemesUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class)
class ThemeViewModelTest {

    private val getAvailableThemesUseCase: GetAvailableThemesUseCase = mock()
    private val router: ThemeRouter = mock()

    private val viewModel by lazy { ThemeViewModel(getAvailableThemesUseCase, router) }

    private val themesObserver: Observer<List<Theme>> = mock()

    @Test
    fun `init EXPECT themes`() {
        val expected = listOf(Theme.LIGHT, Theme.DARK)
        whenever(getAvailableThemesUseCase()).thenReturn(expected)
        viewModel.themes.observeForever(themesObserver)

        verify(themesObserver).onChanged(expected)
    }

    @Test
    fun `close EXPECT router close`() {
        viewModel.close()

        verify(router).close()
    }
}