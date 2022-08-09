package com.shellwoo.kinoguru.feature.splash.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SplashViewModelTest {

    @BeforeEach
    fun beforeEach() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
    }

    private val viewModel: SplashViewModel by lazy { SplashViewModel() }

    @Test
    fun `delay EXPECT complete delay`() = runTest {
        viewModel.delay()
    }
}