package com.shellwoo.kinoguru.app.presentation

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class MainViewModelTest {

    private val router: MainRouter = mock()

    private val viewModel = MainViewModel(router)

    @Test
    fun `open splash screen EXPECT view model open splash screen`() {
        viewModel.openSplashScreen()

        verify(router).openSplashScreen()
    }
}