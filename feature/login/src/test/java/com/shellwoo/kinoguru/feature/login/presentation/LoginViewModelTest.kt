package com.shellwoo.kinoguru.feature.login.presentation

import android.app.PendingIntent
import android.content.Intent
import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.feature.login.domain.usecase.GetGoogleOneTapSignInIntentUseCase
import com.shellwoo.kinoguru.feature.login.domain.usecase.GetGoogleStandardSignInIntentUseCase
import com.shellwoo.kinoguru.feature.login.domain.usecase.GoogleSignInUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class LoginViewModelTest {

    private val getGoogleOneTapSignInIntentUseCase: GetGoogleOneTapSignInIntentUseCase = mock()
    private val getGoogleStandardSignInIntentUseCase: GetGoogleStandardSignInIntentUseCase = mock()
    private val googleSignInUseCase: GoogleSignInUseCase = mock()
    private val viewModel = LoginViewModel(
        getGoogleOneTapSignInIntentUseCase,
        getGoogleStandardSignInIntentUseCase,
        googleSignInUseCase,
    )

    private val requestSignInEventObserver = Observer<PendingIntent> {}
    private val pendingIntent: PendingIntent = mock()

    @Test
    fun `request google one tap sign in EXPECT request sign in event`() = runTest {
        whenever(getGoogleOneTapSignInIntentUseCase.invoke()).thenReturn(pendingIntent)
        viewModel.requestSignInEvent.observeForever(requestSignInEventObserver)

        viewModel.requestGoogleOneTapSignIn()

        requestSignInEventObserver.onChanged(pendingIntent)
    }

    @Test
    fun `request google standard sign in EXPECT request sign in event`() = runTest {
        whenever(getGoogleStandardSignInIntentUseCase.invoke()).thenReturn(pendingIntent)
        viewModel.requestSignInEvent.observeForever(requestSignInEventObserver)

        viewModel.requestGoogleStandardSignIn()

        requestSignInEventObserver.onChanged(pendingIntent)
    }

    @Test
    fun `handle request sign in result EXPECT complete`() = runTest {
        val intent: Intent = mock()

        viewModel.handleRequestSignInResult(intent)
    }
}