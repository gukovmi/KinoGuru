package com.shellwoo.kinoguru.feature.login.domain.repository

import android.content.Intent
import com.google.firebase.auth.AuthCredential
import com.shellwoo.kinoguru.feature.login.domain.entity.AuthSourceVariant

interface AuthCredentialRepository {

    fun get(intent: Intent?, variant: AuthSourceVariant): AuthCredential

    suspend fun set(authCredential: AuthCredential)
}