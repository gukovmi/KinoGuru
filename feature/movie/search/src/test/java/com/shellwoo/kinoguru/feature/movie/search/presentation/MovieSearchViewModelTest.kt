package com.shellwoo.kinoguru.feature.movie.search.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.core.test.unit.thenNeverAnswer
import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearch
import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearchResult
import com.shellwoo.kinoguru.feature.movie.search.domain.scenario.GetMovieSearchResultScenario
import com.shellwoo.kinoguru.feature.movie.search.domain.usecase.IsMovieSearchOnboardingShowedUseCase
import com.shellwoo.kinoguru.feature.movie.search.domain.usecase.SetMovieSearchOnboardingShowedUseCase
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import com.shellwoo.kinoguru.shared.error.domain.usecase.GetBaseExceptionUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class MovieSearchViewModelTest {

    private val isMovieSearchOnboardingShowedUseCase: IsMovieSearchOnboardingShowedUseCase = mock()
    private val setMovieSearchOnboardingShowedUseCase: SetMovieSearchOnboardingShowedUseCase = mock()
    private val getMovieSearchResultScenario: GetMovieSearchResultScenario = mock()
    private val getBaseExceptionUseCase: GetBaseExceptionUseCase = mock()
    private val router: MovieSearchRouter = mock()

    private val viewModel = MovieSearchViewModel(
        isMovieSearchOnboardingShowedUseCase,
        setMovieSearchOnboardingShowedUseCase,
        getMovieSearchResultScenario,
        getBaseExceptionUseCase,
        router,
    )

    private val stateObserver: Observer<ScreenState> = mock()
    private val onboardingEventObserver: Observer<Unit> = mock()
    private val searchErrorEventObserver: Observer<BaseException> = mock()

    private val query: String = "Batman"
    private val movieSearch: MovieSearch = mock()
    private val movieSearches: List<MovieSearch> = List(20) { movieSearch }
    private val movieSearchResult: MovieSearchResult = mock {
        on { it.movies } doReturn movieSearches
    }
    private val movieSearchItemSuccess = MovieSearchItem.Success(movieSearch)
    private val movieSearchItemsLoading = List(20) { MovieSearchItem.Loading }
    private val movieSearchItemsSuccess = List(20) { MovieSearchItem.Success(movieSearch) }

    @Test
    fun `init EXPECT initial state`() {
        viewModel.state.observeForever(stateObserver)

        verify(stateObserver).onChanged(ScreenState.Initial)
    }

    @Test
    fun `start EXPECT content state with none search movie state`() = runTest {
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        val expectedContent = ScreenState.Content("", SearchState.None)
        viewModel.state.observeForever(stateObserver)

        viewModel.start()

        verify(stateObserver).onChanged(expectedContent)
    }

    @Test
    fun `start, search onboarding was not showed EXPECT onboarding event`() = runTest {
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(false))
        viewModel.onboardingEvent.observeForever(onboardingEventObserver)

        viewModel.start()

        verify(onboardingEventObserver).onChanged(Unit)
    }

    @Test
    fun `start, search onboarding was not showed EXPECT set search onboarding showed`() = runTest {
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(false))

        viewModel.start()

        verify(setMovieSearchOnboardingShowedUseCase).invoke()
    }

    @Test
    fun `start, search onboarding was showed EXPECT onboarding event never emit`() = runTest {
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        viewModel.onboardingEvent.observeForever(onboardingEventObserver)

        viewModel.start()

        verify(onboardingEventObserver, never()).onChanged(Unit)
    }

    @Test
    fun `start, search onboarding not showed EXPECT set search onboarding showed never call`() = runTest {
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))

        viewModel.start()

        verify(setMovieSearchOnboardingShowedUseCase, never()).invoke()
    }

    @Test
    fun `set new query, wait debounce, EXPECT content with loading items in result search state`() = runTest {
        val expectedSearchState = SearchState.Items(movieSearchItemsLoading)
        val expectedContentState = ScreenState.Content(query, expectedSearchState)
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario(query)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)
        viewModel.start()

        viewModel.setQuery(query)
        advanceTimeBy(510L)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `set old query, wait debounce EXPECT content with not loading items in result search state`() = runTest {
        val unexpectedSearchState = SearchState.Items(movieSearchItemsLoading)
        val unexpectedContentState = ScreenState.Content(query, unexpectedSearchState)
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario(query)).thenReturn(movieSearchResult)
        viewModel.state.observeForever(stateObserver)
        viewModel.start()
        viewModel.setQuery(query)
        advanceTimeBy(510L)
        clearInvocations(stateObserver)

        viewModel.setQuery(query)
        advanceTimeBy(510L)

        verify(stateObserver, never()).onChanged(unexpectedContentState)
    }

    @Test
    fun `set empty query, wait debounce EXPECT content with none search state`() = runTest {
        val expectedSearchState = SearchState.None
        val expectedContentState = ScreenState.Content("", expectedSearchState)
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario(query)).thenReturn(movieSearchResult)
        viewModel.state.observeForever(stateObserver)
        viewModel.start()
        viewModel.setQuery(query)
        advanceTimeBy(510L)
        clearInvocations(stateObserver)

        viewModel.setQuery("")
        advanceTimeBy(510L)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `set query, wait debounce, EXPECT content with loading items in search state`() = runTest {
        val expectedSearchState = SearchState.Items(movieSearchItemsLoading)
        val expectedContentState = ScreenState.Content(query, expectedSearchState)
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario(query)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)
        viewModel.start()

        viewModel.setQuery(query)
        advanceTimeBy(510L)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `set query, wait debounce, movies was found EXPECT content with success items in search state`() = runTest {
        val expectedSearchState = SearchState.Items(movieSearchItemsSuccess)
        val expectedContentState = ScreenState.Content(query, expectedSearchState)
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario(query)).thenReturn(movieSearchResult)
        viewModel.state.observeForever(stateObserver)
        viewModel.start()

        viewModel.setQuery(query)
        advanceTimeBy(510L)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `set query, wait debounce, movies was not found EXPECT content with empty search state`() = runTest {
        val expectedSearchState = SearchState.NotFound
        val expectedContentState = ScreenState.Content(query, expectedSearchState)
        val emptyMovieSearchResult = MovieSearchResult(1, emptyList())
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario(query)).thenReturn(emptyMovieSearchResult)
        viewModel.state.observeForever(stateObserver)
        viewModel.start()

        viewModel.setQuery(query)
        advanceTimeBy(510L)

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `search, error EXPECT search error event`() = runTest {
        val error = RuntimeException()
        val baseException = ConnectException.ClientConnectException
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario("")).thenThrow(error)
        whenever(getBaseExceptionUseCase(error)).thenReturn(baseException)
        viewModel.searchErrorEvent.observeForever(searchErrorEventObserver)
        viewModel.start()

        viewModel.search()

        verify(searchErrorEventObserver).onChanged(baseException)
    }

    @Test
    fun `search, error EXPECT content with none search state`() = runTest {
        val expectedSearchState = SearchState.None
        val expectedContentState = ScreenState.Content(query = "", searchState = expectedSearchState)
        whenever(isMovieSearchOnboardingShowedUseCase()).thenReturn(flowOf(true))
        whenever(getMovieSearchResultScenario("")).thenThrow(RuntimeException())
        viewModel.state.observeForever(stateObserver)
        viewModel.start()
        clearInvocations(stateObserver)

        viewModel.search()

        verify(stateObserver).onChanged(expectedContentState)
    }

    @Test
    fun `select movie success item EXPECT router open movie details screen`() = runTest {
        viewModel.selectMovieSuccessItem(movieSearchItemSuccess)

        verify(router).openMovieDetailsScreen(movieSearch.id)
    }
}