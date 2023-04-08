package com.shellwoo.kinoguru.feature.search.domain.usecase

import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import javax.inject.Inject

class SetSearchOnboardingShowedUseCase @Inject constructor(
    private val repository: OnboardingShowingRepository,
) : suspend () -> Unit by repository::setShowed