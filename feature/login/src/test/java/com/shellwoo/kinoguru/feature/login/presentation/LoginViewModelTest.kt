package com.shellwoo.kinoguru.feature.login.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class LoginViewModelTest {

    private val viewModel = LoginViewModel()

    private val requestSignInEventObserver = Observer<Unit> {}

    @Test
    fun `request google one tap sign in EXPECT request google one tap sign in event`() = runTest {
        viewModel.requestGoogleOneTapSignInEvent.observeForever(requestSignInEventObserver)

        viewModel.requestGoogleOneTapSignIn()

        requestSignInEventObserver.onChanged(Unit)
    }

    @Test
    fun `request google standard sign in EXPECT request google standard sign in event`() = runTest {
        viewModel.requestGoogleStandardSignInEvent.observeForever(requestSignInEventObserver)

        viewModel.requestGoogleStandardSignIn()

        requestSignInEventObserver.onChanged(Unit)
    }
}