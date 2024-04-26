package com.shellwoo.kinoguru.shared.user.data.repository

import android.app.Activity
import android.net.Uri
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.shellwoo.kinoguru.shared.user.domain.entity.User
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException
import java.util.concurrent.Executor

class UserRepositoryImplTest {

    private val firebaseAuth: FirebaseAuth = mock()

    private val repository = UserRepositoryImpl(firebaseAuth)

    private val successVoidTask = object : Task<Void>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in Void>): Task<Void> {
            onSuccessListener.onSuccess(null)
            return this
        }

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in Void>): Task<Void> {
            onSuccessListener.onSuccess(null)
            return this
        }

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in Void>): Task<Void> {
            onSuccessListener.onSuccess(null)
            return this
        }

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<Void> = this

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<Void> = this

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<Void> = this

        override fun getException(): Exception? = null

        override fun getResult(): Void? = null

        override fun <X : Throwable?> getResult(p0: Class<X>): Void? = null

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = true
    }

    private val ioException = IOException()

    private val failureVoidTask = object : Task<Void>() {

        override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in Void>): Task<Void> = this

        override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in Void>): Task<Void> = this

        override fun addOnSuccessListener(activity: Activity, onSuccessListener: OnSuccessListener<in Void>): Task<Void> = this

        override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<Void> {
            onFailureListener.onFailure(ioException)
            return this
        }

        override fun addOnFailureListener(activity: Activity, onFailureListener: OnFailureListener): Task<Void> {
            onFailureListener.onFailure(ioException)
            return this
        }

        override fun addOnFailureListener(executor: Executor, onFailureListener: OnFailureListener): Task<Void> {
            onFailureListener.onFailure(ioException)
            return this
        }

        override fun getException(): Exception = ioException

        override fun getResult(): Void? = null

        override fun <X : Throwable?> getResult(p0: Class<X>): Void? = null

        override fun isCanceled(): Boolean = false

        override fun isComplete(): Boolean = true

        override fun isSuccessful(): Boolean = false
    }

    private val photoUrl = "photoUrl"

    @Test
    fun `get, firebase user exists EXPECT user`() {
        val name = "Max"
        val email = "MaxMax@gmail.com"
        val expected = User(
            name = name,
            email = email,
            photoUrl = photoUrl,
        )
        val photoUri: Uri = mock {
            on(Uri::toString).thenReturn(photoUrl)
        }
        val firebaseUser: FirebaseUser = mock {
            on(FirebaseUser::getDisplayName).thenReturn(name)
            on(FirebaseUser::getEmail).thenReturn(email)
            on(FirebaseUser::getPhotoUrl).thenReturn(photoUri)
        }
        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)

        val actual = repository.get()

        assertEquals(expected, actual)
    }

    @Test
    fun `get, firebase user doesn't exist EXPECT null`() {
        whenever(firebaseAuth.currentUser).thenReturn(null)

        val actual = repository.get()

        assertNull(actual)
    }

    @Test
    fun `update photo, success EXPECT does not throw exception`() = runTest {
        val uri: Uri = mock()
        val uriClass = mockStatic(Uri::class.java)
        val currentUser: FirebaseUser = mock()
        val userProfileChangeRequest: UserProfileChangeRequest = mock()
        uriClass.`when`<Uri> { Uri.parse(photoUrl) }.thenReturn(uri)
        whenever(firebaseAuth.currentUser).thenReturn(currentUser)
        whenever(currentUser.updateProfile(userProfileChangeRequest)).thenReturn(successVoidTask)
        val userProfileChangeRequestBuilderMockConstruction = mockConstruction(
            UserProfileChangeRequest.Builder::class.java
        ) { mock, _ ->
            `when`(mock.setPhotoUri(uri)).thenReturn(mock)
            `when`(mock.build()).thenReturn(userProfileChangeRequest)
        }

        repository.updatePhoto(photoUrl)

        assertDoesNotThrow { repository.updatePhoto(photoUrl) }

        uriClass.close()
        userProfileChangeRequestBuilderMockConstruction.close()
    }

    @Test
    fun `update photo, failure with io exception EXPECT io exception`() = runTest {
        val uri: Uri = mock()
        val currentUser: FirebaseUser = mock()
        val userProfileChangeRequest: UserProfileChangeRequest = mock()
        val uriClass = mockStatic(Uri::class.java).apply { `when`<Uri> { Uri.parse(photoUrl) }.thenReturn(uri) }
        val userProfileChangeRequestBuilderMockConstruction = mockConstruction(
            UserProfileChangeRequest.Builder::class.java
        ) { mock, _ ->
            `when`(mock.setPhotoUri(uri)).thenReturn(mock)
            `when`(mock.build()).thenReturn(userProfileChangeRequest)
        }
        whenever(firebaseAuth.currentUser).thenReturn(currentUser)
        whenever(currentUser.updateProfile(userProfileChangeRequest)).thenReturn(failureVoidTask)

        assertThrows<IOException> { repository.updatePhoto(photoUrl) }

        uriClass.close()
        userProfileChangeRequestBuilderMockConstruction.close()
    }

    @Test
    fun `update photo, user does not exist EXPECT illegal state exception`() = runTest {
        val uri: Uri = mock()
        val userProfileChangeRequest: UserProfileChangeRequest = mock()
        val uriClass = mockStatic(Uri::class.java).apply { `when`<Uri> { Uri.parse(photoUrl) }.thenReturn(uri) }
        val userProfileChangeRequestBuilderMockConstruction = mockConstruction(
            UserProfileChangeRequest.Builder::class.java
        ) { mock, _ ->
            `when`(mock.setPhotoUri(uri)).thenReturn(mock)
            `when`(mock.build()).thenReturn(userProfileChangeRequest)
        }
        whenever(firebaseAuth.currentUser).thenReturn(null)

        assertThrows<IllegalStateException> { repository.updatePhoto(photoUrl) }

        uriClass.close()
        userProfileChangeRequestBuilderMockConstruction.close()
    }
}