package com.shellwoo.kinoguru.feature.splash.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class SplashViewModelTest {

    private val viewModel = SplashViewModel()

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
}