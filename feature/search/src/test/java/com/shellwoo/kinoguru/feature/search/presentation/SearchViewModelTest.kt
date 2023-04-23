package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.core.test.unit.thenNeverAnswer
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.feature.search.domain.usecase.GetSearchMovieResultUseCase
import com.shellwoo.kinoguru.feature.search.domain.usecase.IsSearchOnboardingShowedUseCase
import com.shellwoo.kinoguru.feature.search.domain.usecase.SetSearchOnboardingShowedUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class SearchViewModelTest {

    private val isSearchOnboardingShowedUseCase: IsSearchOnboardingShowedUseCase = mock()
    private val setSearchOnboardingShowedUseCase: SetSearchOnboardingShowedUseCase = mock()
    private val getSearchMovieResultUseCase: GetSearchMovieResultUseCase = mock()

    private val viewModel = SearchViewModel(
        isSearchOnboardingShowedUseCase,
        setSearchOnboardingShowedUseCase,
        getSearchMovieResultUseCase,
    )

    private val stateObserver: Observer<ScreenState> = mock()
    private val onboardingEventObserver: Observer<Unit> = mock()

    @Test
    fun `init EXPECT initial state`() {
        viewModel.state.observeForever(stateObserver)

        verify(stateObserver).onChanged(ScreenState.Initial)
    }

    @Test
    fun `start EXPECT content state with none search movie state`() = runTest {
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        val expectedContent = ScreenState.Content("", SearchState.None)
        viewModel.state.observeForever(stateObserver)

        viewModel.start()

        verify(stateObserver).onChanged(expectedContent)
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

    @Test
    fun `set new query EXPECT content with loading state`() = runTest {
        val query = "Batman"
        val expectedContent = ScreenState.Content(query, SearchState.Loading)
        whenever(isSearchOnboardingShowedUseCase()).thenNeverAnswer()
        whenever(getSearchMovieResultUseCase(query)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)

        viewModel.start()
        viewModel.setQuery(query)

        verify(stateObserver).onChanged(expectedContent)
    }

    @Test
    fun `set old query EXPECT content with not loading state`() = runTest {
        val searchMovieResult: SearchMovieResult = mock()
        val query = "Batman"
        val unexpectedContent = ScreenState.Content(query, SearchState.Loading)
        whenever(isSearchOnboardingShowedUseCase()).thenNeverAnswer()
        whenever(getSearchMovieResultUseCase(query)).thenReturn(searchMovieResult)
        viewModel.state.observeForever(stateObserver)
        viewModel.start()
        viewModel.setQuery(query)
        clearInvocations(stateObserver)

        viewModel.setQuery(query)

        verify(stateObserver, never()).onChanged(unexpectedContent)
    }

    @Test
    fun `search EXPECT content with loading search state`() = runTest {
        val query = "Batman"
        whenever(isSearchOnboardingShowedUseCase()).thenNeverAnswer()
        whenever(getSearchMovieResultUseCase(query)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)

        viewModel.start()
        viewModel.search(query)

        verify(stateObserver).onChanged(ScreenState.Content(query, SearchState.Loading))
    }

    @Test
    fun `search, movies was found EXPECT content with successful search state`() = runTest {
        val searchMovieResult: SearchMovieResult = mock()
        val query = "Batman"
        val expectedContent = ScreenState.Content(query, SearchState.Successful(searchMovieResult))
        whenever(isSearchOnboardingShowedUseCase()).thenNeverAnswer()
        whenever(getSearchMovieResultUseCase(query)).thenReturn(searchMovieResult)
        viewModel.state.observeForever(stateObserver)

        viewModel.start()
        viewModel.search(query)

        verify(stateObserver).onChanged(expectedContent)
    }
}