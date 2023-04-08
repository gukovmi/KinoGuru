package com.shellwoo.kinoguru.shared.onboarding.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingShowingRepository {

    fun isShowed(): Flow<Boolean>

    suspend fun setShowed()
}