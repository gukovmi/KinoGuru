package com.shellwoo.kinoguru.feature.movie.search.data.repository

import com.shellwoo.kinoguru.shared.onboarding.data.datasource.OnboardingShowingDataSource
import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
internal class MovieSearchOnboardingShowingRepositoryImplTest {

    private val dataSource: OnboardingShowingDataSource = mock()

    private val repository: OnboardingShowingRepository = MovieSearchOnboardingShowingRepositoryImpl(dataSource)

    @Test
    fun `is showed EXPECT flow with is showed value from data source`() = runTest {
        val expected = true
        whenever(dataSource.isShowed()).thenReturn(flowOf(expected))

        val actual = repository.isShowed().first()

        assertEquals(expected, actual)
    }

    @Test
    fun `set showed EXPECT data source set showed`() = runTest {
        repository.setShowed()

        verify(dataSource).setShowed()
    }
}