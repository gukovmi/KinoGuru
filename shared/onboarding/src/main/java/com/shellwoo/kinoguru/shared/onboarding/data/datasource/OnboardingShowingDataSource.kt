package com.shellwoo.kinoguru.shared.onboarding.data.datasource

import kotlinx.coroutines.flow.Flow

interface OnboardingShowingDataSource {

    fun isShowed(): Flow<Boolean>

    suspend fun setShowed()
}