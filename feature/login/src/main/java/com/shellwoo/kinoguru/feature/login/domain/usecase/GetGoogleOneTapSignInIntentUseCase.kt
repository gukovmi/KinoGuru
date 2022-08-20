package com.shellwoo.kinoguru.feature.login.domain.usecase

import android.app.PendingIntent
import com.shellwoo.kinoguru.feature.login.domain.entity.GoogleAuthVariant
import com.shellwoo.kinoguru.feature.login.domain.repository.GoogleSignInIntentRepository
import javax.inject.Inject

class GetGoogleOneTapSignInIntentUseCase @Inject constructor(
    private val googleSignInIntentRepository: GoogleSignInIntentRepository,
) {

    suspend operator fun invoke(): PendingIntent =
        googleSignInIntentRepository.get(GoogleAuthVariant.ONE_TAP)
}