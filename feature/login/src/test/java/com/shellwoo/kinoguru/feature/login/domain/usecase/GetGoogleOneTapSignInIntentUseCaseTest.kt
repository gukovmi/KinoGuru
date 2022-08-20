package com.shellwoo.kinoguru.feature.login.domain.usecase

import android.app.PendingIntent
import com.shellwoo.kinoguru.feature.login.domain.entity.GoogleAuthVariant
import com.shellwoo.kinoguru.feature.login.domain.repository.GoogleSignInIntentRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetGoogleOneTapSignInIntentUseCaseTest {

    private val googleSignInIntentRepository: GoogleSignInIntentRepository = mock()
    private val useCase = GetGoogleOneTapSignInIntentUseCase(googleSignInIntentRepository)

    @Test
    fun `invoke EXPECT sign in intent`() = runTest {
        val signInIntent: PendingIntent = mock()
        whenever(googleSignInIntentRepository.get(GoogleAuthVariant.ONE_TAP)).thenReturn(signInIntent)

        val actual = useCase()

        assertEquals(signInIntent, actual)
    }
}