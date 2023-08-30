package com.shellwoo.kinoguru.feature.movie.search.domain.usecase

import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
internal class SetMovieSearchOnboardingShowedUseCaseTest {

    private val repository: OnboardingShowingRepository = mock()

    private val useCase = SetMovieSearchOnboardingShowedUseCase(repository)

    @Test
    fun `invoke EXPECT repository set showed`() = runTest {
        useCase.invoke()

        verify(repository).setShowed()
    }
}