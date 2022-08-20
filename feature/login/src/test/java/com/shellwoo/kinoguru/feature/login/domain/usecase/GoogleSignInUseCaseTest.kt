package com.shellwoo.kinoguru.feature.login.domain.usecase

import android.content.Intent
import com.google.firebase.auth.AuthCredential
import com.shellwoo.kinoguru.feature.login.domain.entity.AuthSourceVariant
import com.shellwoo.kinoguru.feature.login.domain.repository.AuthCredentialRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GoogleSignInUseCaseTest {

    private val authCredentialRepository: AuthCredentialRepository = mock()
    private val useCase = GoogleSignInUseCase(authCredentialRepository)

    @Test
    fun `invoke EXPECT call auth credential repository set`() = runTest {
        val intent: Intent = mock()
        val authCredential: AuthCredential = mock()
        whenever(authCredentialRepository.get(intent, AuthSourceVariant.GOOGLE)).thenReturn(authCredential)

        useCase(intent)

        verify(authCredentialRepository).set(authCredential)
    }
}