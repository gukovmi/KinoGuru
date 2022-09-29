package com.shellwoo.kinoguru.feature.main.presentation

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
class MainViewModelTest {

    private val router: MainRouter = mock()

    private val viewModel = MainViewModel(router)

    @Test
    fun `open profile screen EXPECT router open profile screen`() {
        viewModel.openProfileScreen()

        verify(router).openProfileScreen()
    }
}