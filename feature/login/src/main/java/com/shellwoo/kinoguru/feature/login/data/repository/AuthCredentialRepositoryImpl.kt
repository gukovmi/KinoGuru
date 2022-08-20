package com.shellwoo.kinoguru.feature.login.data.repository

import android.content.Intent
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.shellwoo.kinoguru.feature.login.domain.entity.AuthSourceVariant
import com.shellwoo.kinoguru.feature.login.domain.repository.AuthCredentialRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class AuthCredentialRepositoryImpl @Inject constructor(
    private val signInClient: SignInClient,
    private val firebaseAuth: FirebaseAuth,
) :
    AuthCredentialRepository {

    override fun get(intent: Intent?, variant: AuthSourceVariant): AuthCredential {
        val signInCredential = signInClient.getSignInCredentialFromIntent(intent)

        return when (variant) {
            AuthSourceVariant.GOOGLE -> getFromGoogle(signInCredential)
        }
    }

    private fun getFromGoogle(signInCredential: SignInCredential): AuthCredential {
        val googleIdToken = signInCredential.googleIdToken
        return GoogleAuthProvider.getCredential(googleIdToken, null)
    }

    override suspend fun set(authCredential: AuthCredential): Unit = suspendCancellableCoroutine { continuation ->
        firebaseAuth.signInWithCredential(authCredential)
            .addOnSuccessListener { authResult ->
                continuation.resumeWith(Result.success(Unit))
            }
            .addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }
}