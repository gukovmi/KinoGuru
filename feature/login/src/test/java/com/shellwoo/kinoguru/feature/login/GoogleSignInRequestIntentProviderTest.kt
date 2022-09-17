package com.shellwoo.kinoguru.feature.login

import android.app.Activity
import android.app.PendingIntent
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException
import java.util.concurrent.Executor

class GoogleSignInRequestIntentProviderTest {

    private val signInClient: SignInClient = mock()
    private val provider = GoogleSignInRequestIntentProvider(signInClient)

    private val signInIntent: PendingIntent = mock()
    private val successPendingIntentTask = object : Task<PendingIntent>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in PendingIntent>): Task<PendingIntent> {
            onSuccessListener.onSuccess(signInIntent)
            return this
        }

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in PendingIntent>): Task<PendingIntent> {
            onSuccessListener.onSuccess(signInIntent)
            return this
        }

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in PendingIntent>): Task<PendingIntent> {
            onSuccessListener.onSuccess(signInIntent)
            return this
        }

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<PendingIntent> = this

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<PendingIntent> = this

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<PendingIntent> = this

        override fun getException(): Exception? = null

        override fun getResult(): PendingIntent = signInIntent

        override fun <X : Throwable?> getResult(p0: Class<X>): PendingIntent = signInIntent

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = true
    }

    private val error = IOException()
    private val failurePendingIntentTask = object : Task<PendingIntent>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in PendingIntent>): Task<PendingIntent> = this

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in PendingIntent>): Task<PendingIntent> = this

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in PendingIntent>): Task<PendingIntent> = this

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<PendingIntent> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<PendingIntent> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<PendingIntent> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun getException(): Exception = error

        override fun getResult(): PendingIntent = signInIntent

        override fun <X : Throwable?> getResult(p0: Class<X>): PendingIntent = signInIntent

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = true
    }

    private val beginSignInResult: BeginSignInResult = mock()
    private val successBeginSignInResultTask = object : Task<BeginSignInResult>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in BeginSignInResult>): Task<BeginSignInResult> {
            onSuccessListener.onSuccess(beginSignInResult)
            return this
        }

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in BeginSignInResult>): Task<BeginSignInResult> {
            onSuccessListener.onSuccess(beginSignInResult)
            return this
        }

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in BeginSignInResult>): Task<BeginSignInResult> {
            onSuccessListener.onSuccess(beginSignInResult)
            return this
        }

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<BeginSignInResult> = this

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<BeginSignInResult> = this

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<BeginSignInResult> = this

        override fun getException(): Exception? = null

        override fun getResult(): BeginSignInResult = beginSignInResult

        override fun <X : Throwable?> getResult(p0: Class<X>): BeginSignInResult = beginSignInResult

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = true
    }

    private val failureBeginSignInResultTask = object : Task<BeginSignInResult>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in BeginSignInResult>): Task<BeginSignInResult> = this

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in BeginSignInResult>): Task<BeginSignInResult> = this

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in BeginSignInResult>): Task<BeginSignInResult> = this

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<BeginSignInResult> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<BeginSignInResult> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<BeginSignInResult> {
            onFailureListener.onFailure(error)
            return this
        }

        override fun getException(): Exception = error

        override fun getResult(): BeginSignInResult = beginSignInResult

        override fun <X : Throwable?> getResult(p0: Class<X>): BeginSignInResult = beginSignInResult

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = true
    }

    @Test
    fun `get with standard variant, success EXPECT sign in intent`() = runTest {
        whenever(signInClient.getSignInIntent(any())).thenReturn(successPendingIntentTask)
        val actual = provider.get(GoogleAuthVariant.STANDARD)

        Assertions.assertEquals(signInIntent, actual)
    }

    @Test
    fun `get with standard variant, failure EXPECT error`() = runTest {
        whenever(signInClient.getSignInIntent(any())).thenReturn(failurePendingIntentTask)

        assertThrows<IOException> { provider.get(GoogleAuthVariant.STANDARD) }
    }

    @Test
    fun `get with one tap variant, success EXPECT sign in intent`() = runTest {
        whenever(signInClient.beginSignIn(any())).thenReturn(successBeginSignInResultTask)
        whenever(beginSignInResult.pendingIntent).thenReturn(signInIntent)

        val actual = provider.get(GoogleAuthVariant.ONE_TAP)

        Assertions.assertEquals(signInIntent, actual)
    }

    @Test
    fun `get with one tap variant, failure EXPECT error`() = runTest {
        whenever(signInClient.beginSignIn(any())).thenReturn(failureBeginSignInResultTask)

        assertThrows<IOException> { provider.get(GoogleAuthVariant.ONE_TAP) }
    }
}