package com.shellwoo.kinoguru.feature.login.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class LoginViewModelTest {

    private val viewModel = LoginViewModel()

    private val unitObserver = Observer<Unit> {}

    @Test
    fun `request google one tap sign in EXPECT request google one tap sign in event`() {
        viewModel.requestGoogleOneTapSignInEvent.observeForever(unitObserver)

        viewModel.requestGoogleOneTapSignIn()

        unitObserver.onChanged(Unit)
    }

    @Test
    fun `request google standard sign in EXPECT request google standard sign in event`() {
        viewModel.requestGoogleStandardSignInEvent.observeForever(unitObserver)

        viewModel.requestGoogleStandardSignIn()

        unitObserver.onChanged(Unit)
    }

    @Test
    fun `handle sign in error EXPECT sign in error event`() {
        viewModel.signInErrorEvent.observeForever(unitObserver)

        viewModel.handleSignInError()

        unitObserver.onChanged(Unit)
    }
}