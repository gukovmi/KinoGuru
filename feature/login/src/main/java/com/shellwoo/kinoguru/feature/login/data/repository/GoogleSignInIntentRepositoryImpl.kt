package com.shellwoo.kinoguru.feature.login.data.repository

import android.app.PendingIntent
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.shellwoo.kinoguru.feature.login.BuildConfig
import com.shellwoo.kinoguru.feature.login.domain.entity.GoogleAuthVariant
import com.shellwoo.kinoguru.feature.login.domain.repository.GoogleSignInIntentRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class GoogleSignInIntentRepositoryImpl @Inject constructor(
    private val signInClient: SignInClient,
) : GoogleSignInIntentRepository {

    private companion object {
        val STANDARD_REQUEST = GetSignInIntentRequest.builder()
            .setServerClientId(BuildConfig.FIREBASE_SERVER_CLIENT_ID)
            .build()

        val ID_TOKEN_REQUEST_OPTIONS = BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setServerClientId(BuildConfig.FIREBASE_SERVER_CLIENT_ID)
            .setFilterByAuthorizedAccounts(true)
            .build()
        val ONE_TAP_REQUEST = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(ID_TOKEN_REQUEST_OPTIONS)
            .build()
    }

    override suspend fun get(variant: GoogleAuthVariant): PendingIntent =
        when (variant) {
            GoogleAuthVariant.STANDARD -> getStandard()
            GoogleAuthVariant.ONE_TAP -> getOneTap()
        }

    private suspend fun getStandard(): PendingIntent = suspendCancellableCoroutine { continuation ->
        signInClient.getSignInIntent(STANDARD_REQUEST)
            .addOnSuccessListener { pendingIntent ->
                continuation.resumeWith(Result.success(pendingIntent))
            }
            .addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }

    private suspend fun getOneTap(): PendingIntent = suspendCancellableCoroutine { continuation ->
        signInClient.beginSignIn(ONE_TAP_REQUEST)
            .addOnSuccessListener { beginSignInResult ->
                continuation.resumeWith(Result.success(beginSignInResult.pendingIntent))
            }
            .addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
    }
}