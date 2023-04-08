package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.feature.search.domain.usecase.IsSearchOnboardingShowedUseCase
import com.shellwoo.kinoguru.feature.search.domain.usecase.SetSearchOnboardingShowedUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class SearchViewModelTest {

    private val isSearchOnboardingShowedUseCase: IsSearchOnboardingShowedUseCase = mock()
    private val setSearchOnboardingShowedUseCase: SetSearchOnboardingShowedUseCase = mock()

    private val viewModel = SearchViewModel(
        isSearchOnboardingShowedUseCase,
        setSearchOnboardingShowedUseCase,
    )

    private val stateObserver: Observer<SearchState> = mock()
    private val onboardingEventObserver: Observer<Unit> = mock()

    @Test
    fun `init EXPECT initial state`() {
        viewModel.state.observeForever(stateObserver)

        verify(stateObserver).onChanged(SearchState.Initial)
    }

    @Test
    fun `start EXPECT content state`() = runTest {
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        viewModel.state.observeForever(stateObserver)

        viewModel.start()

        verify(stateObserver).onChanged(SearchState.Content)
    }

    @Test
    fun `start, search onboarding was not showed EXPECT onboarding event`() = runTest {
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(false))
        viewModel.onboardingEvent.observeForever(onboardingEventObserver)

        viewModel.start()

        verify(onboardingEventObserver).onChanged(Unit)
    }

    @Test
    fun `start, search onboarding was not showed EXPECT set search onboarding showed`() = runTest {
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(false))

        viewModel.start()

        verify(setSearchOnboardingShowedUseCase).invoke()
    }

    @Test
    fun `start, search onboarding was showed EXPECT onboarding event never emit`() = runTest {
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        viewModel.onboardingEvent.observeForever(onboardingEventObserver)

        viewModel.start()

        verify(onboardingEventObserver, never()).onChanged(Unit)
    }

    @Test
    fun `start, search onboarding not showed EXPECT set search onboarding showed never call`() = runTest {
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))

        viewModel.start()

        verify(setSearchOnboardingShowedUseCase, never()).invoke()
    }
}