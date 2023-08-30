package com.shellwoo.kinoguru.feature.movie.search.data.repository

import com.shellwoo.kinoguru.shared.onboarding.data.datasource.OnboardingShowingDataSource
import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchOnboardingShowingRepositoryImpl @Inject constructor(
    private val dataSource: OnboardingShowingDataSource,
) : OnboardingShowingRepository {

    override fun isShowed(): Flow<Boolean> =
        dataSource.isShowed()

    override suspend fun setShowed() {
        dataSource.setShowed()
    }
}