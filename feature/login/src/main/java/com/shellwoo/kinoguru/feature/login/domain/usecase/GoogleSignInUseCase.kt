package com.shellwoo.kinoguru.feature.login.domain.usecase

import android.content.Intent
import com.shellwoo.kinoguru.feature.login.domain.entity.AuthSourceVariant
import com.shellwoo.kinoguru.feature.login.domain.repository.AuthCredentialRepository
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(
    private val authCredentialRepository: AuthCredentialRepository,
) {

    suspend operator fun invoke(intent: Intent?) {
        val authCredential = authCredentialRepository.get(intent, AuthSourceVariant.GOOGLE)
        authCredentialRepository.set(authCredential)
    }
}