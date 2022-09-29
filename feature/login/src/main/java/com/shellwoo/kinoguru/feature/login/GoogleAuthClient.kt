package com.shellwoo.kinoguru.feature.login

import android.content.Intent
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class GoogleAuthClient @Inject constructor(
    private val signInClient: SignInClient,
    private val firebaseAuth: FirebaseAuth,
) {

    suspend fun signIn(data: Intent?): Unit = suspendCancellableCoroutine { continuation ->
        val signInCredential = signInClient.getSignInCredentialFromIntent(data)
        val googleIdToken = signInCredential.googleIdToken
        val authCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

        firebaseAuth.signInWithCredential(authCredential)
            .addOnSuccessListener {
                continuation.resumeWith(Result.success(Unit))
            }
            .addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }
}