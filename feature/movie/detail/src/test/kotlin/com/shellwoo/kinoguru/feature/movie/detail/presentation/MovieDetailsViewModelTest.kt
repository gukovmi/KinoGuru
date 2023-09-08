package com.shellwoo.kinoguru.feature.movie.detail.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.core.test.unit.thenNeverAnswer
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import com.shellwoo.kinoguru.feature.movie.detail.domain.scenario.GetMovieDetailsScenario
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.exception.ClientConnectException
import com.shellwoo.kinoguru.shared.error.domain.usecase.GetBaseExceptionUseCase
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

    private val getBaseExceptionUseCase: GetBaseExceptionUseCase = mock()
    private val getMovieDetailsScenario: GetMovieDetailsScenario = mock()
    private val router: MovieDetailsRouter = mock()
    private val movieId: Int = 123

    private val viewModel = MovieDetailsViewModel(getBaseExceptionUseCase, getMovieDetailsScenario, router, movieId)

    private val stateObserver: Observer<MovieDetailsState> = mock()
    private val loadMovieDetailsErrorEventObserver: Observer<BaseException> = mock()
    private val error = RuntimeException()
    private val baseException = ClientConnectException("")

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
        whenever(getBaseExceptionUseCase(error)).thenReturn(baseException)
        viewModel.state.observeForever(stateObserver)
        clearInvocations(stateObserver)

        viewModel.loadMovieDetails()

        verify(stateObserver).onChanged(MovieDetailsState.Initial)
    }

    @Test
    fun `load movie details with error EXPECT load movie details error event`() = runTest {
        whenever(getMovieDetailsScenario(movieId)).thenThrow(error)
        whenever(getBaseExceptionUseCase(error)).thenReturn(baseException)
        viewModel.loadMovieDetailsErrorEvent.observeForever(loadMovieDetailsErrorEventObserver)

        viewModel.loadMovieDetails()

        verify(loadMovieDetailsErrorEventObserver).onChanged(baseException)
    }

    @Test
    fun `close EXPECT router close`() = runTest {
        viewModel.close()

        verify(router).close()
    }
}