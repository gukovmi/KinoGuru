package com.shellwoo.kinoguru.feature.login

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException
import java.util.concurrent.Executor

class GoogleAuthClientTest {

    private val signInClient: SignInClient = mock()
    private val firebaseAuth: FirebaseAuth = mock()

    private val authClient = GoogleAuthClient(signInClient, firebaseAuth)

    private val authCredential: AuthCredential = mock()
    private val authResult: AuthResult = mock()
    private val successTask = object : Task<AuthResult>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> {
            onSuccessListener.onSuccess(authResult)
            return this
        }

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> {
            onSuccessListener.onSuccess(authResult)
            return this
        }

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> {
            onSuccessListener.onSuccess(authResult)
            return this
        }

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<AuthResult> = this

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<AuthResult> = this

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<AuthResult> = this

        override fun getException(): Exception? = null

        override fun getResult(): AuthResult = authResult

        override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult = authResult

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = true
    }

    private val error = IOException()
    private val failureTask = object : Task<AuthResult>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> = this

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> = this

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> = this

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<AuthResult> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<AuthResult> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<AuthResult> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun getException(): Exception = error

        override fun getResult(): AuthResult = authResult

        override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult = authResult

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = true
    }

    private val intent: Intent = mock()
    private val signInCredential: SignInCredential = mock()

    @Test
    fun `sign in, success EXPECT complete`() = runTest {
        Mockito.mockStatic(GoogleAuthProvider::class.java).use { googleAuthProviderMock ->
            val googleIdToken = "1234"
            whenever(signInClient.getSignInCredentialFromIntent(intent)).thenReturn(signInCredential)
            whenever(signInCredential.googleIdToken).thenReturn(googleIdToken)
            whenever(firebaseAuth.signInWithCredential(authCredential)).thenReturn(successTask)
            googleAuthProviderMock.`when`<AuthCredential> { GoogleAuthProvider.getCredential(googleIdToken, null) }.thenReturn(authCredential)

            authClient.signIn(intent)
        }
    }

    @Test
    fun `sign in, error EXPECT error`() = runTest {
        Mockito.mockStatic(GoogleAuthProvider::class.java).use { googleAuthProviderMock ->
            val googleIdToken = "1234"
            whenever(signInClient.getSignInCredentialFromIntent(intent)).thenReturn(signInCredential)
            whenever(signInCredential.googleIdToken).thenReturn(googleIdToken)
            whenever(firebaseAuth.signInWithCredential(authCredential)).thenReturn(failureTask)
            googleAuthProviderMock.`when`<AuthCredential> { GoogleAuthProvider.getCredential(googleIdToken, null) }.thenReturn(authCredential)

            assertThrows<IOException> { authClient.signIn(intent) }
        }
    }
}