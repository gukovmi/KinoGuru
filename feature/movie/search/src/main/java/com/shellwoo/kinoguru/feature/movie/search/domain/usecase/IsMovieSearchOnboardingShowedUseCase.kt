package com.shellwoo.kinoguru.feature.movie.search.domain.usecase

import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMovieSearchOnboardingShowedUseCase @Inject constructor(
    private val repository: OnboardingShowingRepository,
) : () -> Flow<Boolean> by repository::isShowed