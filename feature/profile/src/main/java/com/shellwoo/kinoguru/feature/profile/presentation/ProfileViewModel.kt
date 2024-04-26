package com.shellwoo.kinoguru.feature.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.feature.profile.domain.entity.Profile
import com.shellwoo.kinoguru.feature.profile.domain.scenario.GetProfileScenario
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.usecase.SetCurrentLanguageUseCase
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.usecase.SetCurrentThemeUseCase
import com.shellwoo.kinoguru.shared.user.domain.usecase.UpdateUserPhotoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val setCurrentLanguageUseCase: SetCurrentLanguageUseCase,
    private val setCurrentThemeUseCase: SetCurrentThemeUseCase,
    private val updateUserPhotoUseCase: UpdateUserPhotoUseCase,
    private val getProfileScenario: GetProfileScenario,
    private val router: ProfileRouter,
) : ViewModel() {

    private val _state = MutableLiveData<ProfileState>(ProfileState.Initial)
    val state: LiveData<ProfileState> = _state

    fun selectLanguage(language: Language) {
        setCurrentLanguageUseCase(language)

        loadProfile()
    }

    fun selectTheme(theme: Theme) {
        viewModelScope.launch {
            setCurrentThemeUseCase(theme)

            loadProfile()
        }
    }

    fun loadProfile() {
        _state.value = ProfileState.Loading

        val profile = getProfileScenario()
        handleProfile(profile)
    }

    private fun handleProfile(profile: Profile?) {
        if (profile != null) {
            _state.value = ProfileState.Content(
                name = profile.name,
                email = profile.email,
                photoUrl = profile.photoUrl,
                language = profile.language,
                theme = profile.theme,
            )
        } else {
            router.openLoginScreen()
        }
    }

    fun updateUserPhoto(url: String) {
        viewModelScope.launch {
            updateUserPhotoUseCase(url)

            loadProfile()
        }
    }

    fun openLanguageScreen() {
        router.openLanguageScreen()
    }

    fun openThemeScreen() {
        router.openThemeScreen()
    }
}