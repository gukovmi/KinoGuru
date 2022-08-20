package com.shellwoo.kinoguru.feature.login.domain.repository

import android.app.PendingIntent
import com.shellwoo.kinoguru.feature.login.domain.entity.GoogleAuthVariant

interface GoogleSignInIntentRepository {

    suspend fun get(variant: GoogleAuthVariant): PendingIntent
}