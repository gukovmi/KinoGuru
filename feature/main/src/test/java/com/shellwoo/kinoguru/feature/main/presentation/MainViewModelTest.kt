package com.shellwoo.kinoguru.feature.main.presentation

import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class)
class MainViewModelTest {

    private val router: MainRouter = mock()

    private val viewModel by lazy { MainViewModel(router) }

    @Test
    fun `init EXPECT state with all tabs and search tab selected`() {
        val expected = MainState(Tab.SEARCH, Tab.entries)

        val actual = viewModel.state.value

        assertEquals(expected, actual)
    }

    @Test
    fun `init EXPECT router open search screen`() {
        viewModel

        verify(router).openSearchScreen()
    }

    @Test
    fun `select tab with profile tab EXPECT router open profile screen`() {
        viewModel.selectTab(Tab.PROFILE)

        verify(router).openProfileScreen()
    }

    @Test
    fun `select tab with already selected tab EXPECT router without invocations`() {
        viewModel
        clearInvocations(router)

        viewModel.selectTab(Tab.SEARCH)

        verifyNoInteractions(router)
    }

    @Test
    fun `elect tab with search tab EXPECT router open search screen`() {
        viewModel.selectTab(Tab.PROFILE)
        clearInvocations(router)

        viewModel.selectTab(Tab.SEARCH)

        verify(router).openSearchScreen()
    }
}