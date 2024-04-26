package com.shellwoo.kinoguru.shared.user.data.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : UserRepository {

    override fun get(): User? {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        return if (firebaseUser != null) {
            User(
                name = firebaseUser.displayName,
                email = firebaseUser.email,
                photoUrl = firebaseUser.photoUrl?.toString(),
            )
        } else {
            null
        }
    }

    override suspend fun updatePhoto(url: String) =
        suspendCoroutine<Unit> { continuation ->
            val profileUpdateRequest = UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(url))
                .build()

            firebaseAuth.currentUser?.updateProfile(profileUpdateRequest)
                ?.addOnSuccessListener {
                    continuation.resume(Unit)
                }
                ?.addOnFailureListener(continuation::resumeWithException)
                ?: continuation.resumeWithException(IllegalStateException("Firebase user wasn't found"))
        }
}