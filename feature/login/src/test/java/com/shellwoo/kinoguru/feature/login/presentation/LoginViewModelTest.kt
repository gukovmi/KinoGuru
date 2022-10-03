package com.shellwoo.kinoguru.feature.login.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class LoginViewModelTest {

    private val router: LoginRouter = mock()

    private val viewModel = LoginViewModel(router)

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

    @Test
    fun `open main screen EXPECT router open main screen`() {
        viewModel.openMainScreen()

        verify(router).openMainScreen()
    }
}