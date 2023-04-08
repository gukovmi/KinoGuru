package com.shellwoo.kinoguru.feature.search.domain.usecase

import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class IsSearchOnboardingShowedUseCaseTest {

    private val repository: OnboardingShowingRepository = mock()

    private val useCase = IsSearchOnboardingShowedUseCase(repository)

    @Test
    fun `invoke EXPECT flow with is showed value`() = runTest {
        val expected = true
        whenever(repository.isShowed()).thenReturn(flowOf(expected))

        val actual = useCase.invoke().first()

        assertEquals(expected, actual)
    }
}