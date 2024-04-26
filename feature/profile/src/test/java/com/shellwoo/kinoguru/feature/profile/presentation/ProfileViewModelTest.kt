package com.shellwoo.kinoguru.feature.profile.presentation

import androidx.lifecycle.Observer
import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.feature.profile.domain.entity.Profile
import com.shellwoo.kinoguru.feature.profile.domain.scenario.GetProfileScenario
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.usecase.SetCurrentLanguageUseCase
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.usecase.SetCurrentThemeUseCase
import com.shellwoo.kinoguru.shared.user.domain.usecase.UpdateUserPhotoUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class ProfileViewModelTest {

    private val setCurrentLanguageUseCase: SetCurrentLanguageUseCase = mock()
    private val setCurrentThemeUseCase: SetCurrentThemeUseCase = mock()
    private val updateUserPhotoUseCase: UpdateUserPhotoUseCase = mock()
    private val getProfileScenario: GetProfileScenario = mock()
    private val router: ProfileRouter = mock()

    private val viewModel = ProfileViewModel(
        setCurrentLanguageUseCase,
        setCurrentThemeUseCase,
        updateUserPhotoUseCase,
        getProfileScenario,
        router,
    )

    private val stateObserver: Observer<ProfileState> = mock()

    @Test
    fun `init EXPECT initial state`() {
        viewModel.state.observeForever(stateObserver)

        verify(stateObserver).onChanged(ProfileState.Initial)
    }

    @Test
    fun `load profile EXPECT loading state`() {
        viewModel.state.observeForever(stateObserver)

        viewModel.loadProfile()

        verify(stateObserver).onChanged(ProfileState.Loading)
    }

    @Test
    fun `load profile, profile is not null EXPECT content state`() {
        val profile = Profile(
            name = "Max",
            email = "max@gmail.com",
            photoUrl = "google.com/images",
            language = Language.ENGLISH,
            theme = Theme.DARK,
        )
        val contentState = ProfileState.Content(
            name = profile.name,
            email = profile.email,
            photoUrl = profile.photoUrl,
            language = profile.language,
            theme = Theme.DARK,
        )
        whenever(getProfileScenario()).thenReturn(profile)
        viewModel.state.observeForever(stateObserver)

        viewModel.loadProfile()

        verify(stateObserver).onChanged(contentState)
    }

    @Test
    fun `load initial data, profile is null EXPECT router open login screen`() {
        whenever(getProfileScenario()).thenReturn(null)

        viewModel.loadProfile()

        verify(router).openLoginScreen()
    }

    @Test
    fun `update user photo EXPECT update user photo`() = runTest {
        val photoUrl = "photoUrl"

        viewModel.updateUserPhoto(photoUrl)

        verify(updateUserPhotoUseCase).invoke(photoUrl)
    }

    @Test
    fun `update user photo EXPECT loading state`() = runTest {
        viewModel.state.observeForever(stateObserver)

        viewModel.updateUserPhoto("photoUrl")

        verify(stateObserver).onChanged(ProfileState.Loading)
    }

    @Test
    fun `open language screen EXPECT router open language screen`() {
        viewModel.openLanguageScreen()

        verify(router).openLanguageScreen()
    }

    @Test
    fun `select language EXPECT set current language`() {
        val language = Language.ENGLISH
        viewModel.selectLanguage(language)

        verify(setCurrentLanguageUseCase).invoke(language)
    }

    @Test
    fun `select language EXPECT loading state`() {
        viewModel.state.observeForever(stateObserver)

        viewModel.selectLanguage(Language.ENGLISH)

        verify(stateObserver).onChanged(ProfileState.Loading)
    }

    @Test
    fun `select theme EXPECT set current theme`() = runTest {
        val theme = Theme.DARK
        viewModel.selectTheme(theme)

        verify(setCurrentThemeUseCase).invoke(theme)
    }

    @Test
    fun `select theme EXPECT loading state`() {
        viewModel.state.observeForever(stateObserver)

        viewModel.selectTheme(Theme.DARK)

        verify(stateObserver).onChanged(ProfileState.Loading)
    }
}