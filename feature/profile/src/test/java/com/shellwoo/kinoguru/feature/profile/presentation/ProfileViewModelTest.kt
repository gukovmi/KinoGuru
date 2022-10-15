package com.shellwoo.kinoguru.feature.profile.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class ProfileViewModelTest {

    private val getCurrentUserUseCase: GetCurrentUserUseCase = mock()
    private val router: ProfileRouter = mock()

    private val viewModel = ProfileViewModel(getCurrentUserUseCase, router)

    private val stateObserver: Observer<ProfileState> = mock()

    @Test
    fun `init EXPECT initial state`() {
        viewModel.state.observeForever(stateObserver)

        verify(stateObserver).onChanged(ProfileState.Initial)
    }

    @Test
    fun `load initial data EXPECT loading state`() {
        viewModel.state.observeForever(stateObserver)

        viewModel.loadInitialData()

        verify(stateObserver).onChanged(ProfileState.Loading)
    }

    @Test
    fun `load initial data, current user exist EXPECT content state`() {
        val user = User(
            name = "Max",
            email = "123@gmail.com",
            photoUrl = "google.com/image",
        )
        val contentState = ProfileState.Content(
            name = user.name,
            email = user.email,
            photoUrl = user.photoUrl,
        )
        whenever(getCurrentUserUseCase()).thenReturn(user)
        viewModel.state.observeForever(stateObserver)

        viewModel.loadInitialData()

        verify(stateObserver).onChanged(contentState)
    }

    @Test
    fun `load initial data, current user not exist EXPECT router open login screen`() {
        whenever(getCurrentUserUseCase()).thenReturn(null)

        viewModel.loadInitialData()

        verify(router).openLoginScreen()
    }

    @Test
    fun `load initial data, error EXPECT initial data loading state`() {
        whenever(getCurrentUserUseCase()).thenThrow(RuntimeException())
        viewModel.state.observeForever(stateObserver)

        viewModel.loadInitialData()

        verify(stateObserver).onChanged(ProfileState.InitialDataLoadingError)
    }
}