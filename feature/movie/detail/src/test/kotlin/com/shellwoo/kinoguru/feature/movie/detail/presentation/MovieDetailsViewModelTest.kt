package com.shellwoo.kinoguru.feature.movie.detail.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.core.test.unit.thenNeverAnswer
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import com.shellwoo.kinoguru.feature.movie.detail.domain.scenario.GetMovieDetailsScenario
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class MovieDetailsViewModelTest {

    private val getMovieDetailsScenario: GetMovieDetailsScenario = mock()
    private val router: MovieDetailsRouter = mock()
    private val movieId: Int = 123

    private val viewModel = MovieDetailsViewModel(getMovieDetailsScenario, router, movieId)

    private val stateObserver: Observer<MovieDetailsState> = mock()
    private val eventObserver: Observer<Unit> = mock()
    private val error = RuntimeException()

    @Test
    fun `init EXPECT initial state`() {
        viewModel.state.observeForever(stateObserver)

        verify(stateObserver).onChanged(MovieDetailsState.Initial)
    }

    @Test
    fun `start, initial state EXPECT loading state`() = runTest {
        whenever(getMovieDetailsScenario(movieId)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)

        viewModel.start()

        verify(stateObserver).onChanged(MovieDetailsState.Loading)
    }

    @Test
    fun `load movie details EXPECT loading state`() = runTest {
        whenever(getMovieDetailsScenario(movieId)).thenNeverAnswer()
        viewModel.state.observeForever(stateObserver)

        viewModel.loadMovieDetails()

        verify(stateObserver).onChanged(MovieDetailsState.Loading)
    }

    @Test
    fun `load movie details without error EXPECT content state`() = runTest {
        val movieDetails: MovieDetails = mock()
        val contentState = MovieDetailsState.Content(movieDetails)
        whenever(getMovieDetailsScenario(movieId)).thenReturn(movieDetails)
        viewModel.state.observeForever(stateObserver)

        viewModel.loadMovieDetails()

        verify(stateObserver).onChanged(contentState)
    }

    @Test
    fun `load movie details with error EXPECT initial state`() = runTest {
        whenever(getMovieDetailsScenario(movieId)).thenThrow(error)
        viewModel.state.observeForever(stateObserver)
        clearInvocations(stateObserver)

        viewModel.loadMovieDetails()

        verify(stateObserver).onChanged(MovieDetailsState.Initial)
    }

    @Test
    fun `load movie details with error EXPECT load movie details error event`() = runTest {
        whenever(getMovieDetailsScenario(movieId)).thenThrow(error)
        viewModel.loadMovieDetailsErrorEvent.observeForever(eventObserver)

        viewModel.loadMovieDetails()

        verify(eventObserver).onChanged(Unit)
    }

    @Test
    fun `close EXPECT router close`() = runTest {
        viewModel.close()

        verify(router).close()
    }
}