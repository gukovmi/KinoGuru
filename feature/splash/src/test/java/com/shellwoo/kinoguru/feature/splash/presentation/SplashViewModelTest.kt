package com.shellwoo.kinoguru.feature.splash.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class SplashViewModelTest {

    private companion object {

        const val DELAY = 2000L
    }

    private val getCurrentUserUseCase: GetCurrentUserUseCase = mock()
    private val router: SplashRouter = mock()
    private val viewModel = SplashViewModel(getCurrentUserUseCase, router)

    private val stateObserver = Observer<SplashState> {}

    @Test
    fun `init EXPECT change state to initial`() = runTest {
        viewModel.state.observeForever(stateObserver)

        stateObserver.onChanged(SplashState.Initial)
    }

    @Test
    fun `start EXPECT change state to content`() = runTest {
        viewModel.state.observeForever(stateObserver)

        viewModel.start()

        stateObserver.onChanged(SplashState.Content)
    }

    @Test
    fun `start, wait delay, current user not exist EXPECT router open login screen`() = runTest {
        whenever(getCurrentUserUseCase.invoke()).thenReturn(null)

        viewModel.start()
        advanceTimeBy(DELAY)
        runCurrent()

        verify(router).openLoginScreen()
    }

    @Test
    fun `start, wait delay, current user exist EXPECT router open main screen`() = runTest {
        val user = User("Max", "123@gmail.com", "google.com/image")
        whenever(getCurrentUserUseCase.invoke()).thenReturn(user)

        viewModel.start()
        advanceTimeBy(DELAY)
        runCurrent()

        verify(router).openMainScreen()
    }
}