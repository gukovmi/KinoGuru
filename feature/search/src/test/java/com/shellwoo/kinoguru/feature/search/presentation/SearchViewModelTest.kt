package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class)
class SearchViewModelTest {

    private val viewModel = SearchViewModel()

    private val stateObserver: Observer<SearchState> = mock()
    private val onboardingEventObserver: Observer<Unit> = mock()

    @Test
    fun `init EXPECT initial state`() {
        viewModel.state.observeForever(stateObserver)

        verify(stateObserver).onChanged(SearchState.Initial)
    }

    @Test
    fun `start EXPECT content state`() {
        viewModel.state.observeForever(stateObserver)

        viewModel.start()

        verify(stateObserver).onChanged(SearchState.Content)
    }

    @Test
    fun `start EXPECT onboarding event`() {
        viewModel.onboardingEvent.observeForever(onboardingEventObserver)

        viewModel.start()

        verify(onboardingEventObserver).onChanged(Unit)
    }
}