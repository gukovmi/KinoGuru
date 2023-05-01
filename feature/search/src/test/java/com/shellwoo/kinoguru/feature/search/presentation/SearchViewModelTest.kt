package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.core.test.unit.thenNeverAnswer
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovie
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
    private val searchErrorEventObserver: Observer<Unit> = mock()

    private val query: String = "Batman"
    private val searchMovie: SearchMovie = mock()
    private val searchMovies: List<SearchMovie> = List(20) { searchMovie }
    private val searchMovieResult: SearchMovieResult = mock {
        on { it.movies } doReturn searchMovies
    }
    private val searchMovieItemsLoading = List(20) { SearchMovieItem.Loading }
    private val searchMovieItemsSuccess = List(20) { SearchMovieItem.Success(searchMovie) }

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
    fun `set new query EXPECT content with loading items in result search state`() = runTest {
        val expectedSearchState = SearchState.Result(searchMovieItemsLoading)
        val expectedContentState = ScreenState.Content(query, expectedSearchState)
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getSearchMovieResultUseCase(query)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)
        viewModel.start()

        viewModel.setQuery(query)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `set old query EXPECT content with not loading items in result search state`() = runTest {
        val searchMovieResult: SearchMovieResult = mock()
        val unexpectedSearchState = SearchState.Result(searchMovieItemsLoading)
        val unexpectedContentState = ScreenState.Content(query, unexpectedSearchState)
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getSearchMovieResultUseCase(query)).thenReturn(searchMovieResult)
        viewModel.state.observeForever(stateObserver)
        viewModel.start()
        viewModel.setQuery(query)
        clearInvocations(stateObserver)

        viewModel.setQuery(query)

        verify(stateObserver, never()).onChanged(unexpectedContentState)
    }

    @Test
    fun `set query EXPECT content with loading items in result search state`() = runTest {
        val expectedSearchState = SearchState.Result(searchMovieItemsLoading)
        val expectedContentState = ScreenState.Content(query, expectedSearchState)
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getSearchMovieResultUseCase(query)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)
        viewModel.start()

        viewModel.setQuery(query)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `set query, movies was found EXPECT content with success items in result search state`() = runTest {
        val expectedSearchState = SearchState.Result(searchMovieItemsSuccess)
        val expectedContentState = ScreenState.Content(query, expectedSearchState)
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getSearchMovieResultUseCase(query)).thenReturn(searchMovieResult)
        viewModel.state.observeForever(stateObserver)
        viewModel.start()

        viewModel.setQuery(query)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `search, error EXPECT search error event`() = runTest {
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getSearchMovieResultUseCase(query)).thenThrow(RuntimeException())
        viewModel.searchErrorEvent.observeForever(searchErrorEventObserver)
        viewModel.start()

        viewModel.search()

        verify(searchErrorEventObserver).onChanged(Unit)
    }

    @Test
    fun `search, error EXPECT content with none search state`() = runTest {
        val expectedSearchState = SearchState.None
        val expectedContentState = ScreenState.Content(query = "", searchState = expectedSearchState)
        whenever(isSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getSearchMovieResultUseCase(query)).thenThrow(RuntimeException())
        viewModel.state.observeForever(stateObserver)
        viewModel.start()
        clearInvocations(stateObserver)

        viewModel.search()

        verify(stateObserver).onChanged(expectedContentState)
    }
}