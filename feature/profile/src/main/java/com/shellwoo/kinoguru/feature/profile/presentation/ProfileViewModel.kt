package com.shellwoo.kinoguru.feature.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.feature.profile.domain.entity.Profile
import com.shellwoo.kinoguru.feature.profile.domain.scenario.GetProfileScenario
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.usecase.SetCurrentLanguageUseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val setCurrentLanguageUseCase: SetCurrentLanguageUseCase,
    private val getProfileScenario: GetProfileScenario,
    private val router: ProfileRouter,
) : ViewModel() {

    private val _state = MutableLiveData<ProfileState>(ProfileState.Initial)
    val state: LiveData<ProfileState> = _state

    fun selectLanguage(language: Language) {
        setCurrentLanguageUseCase(language)

        loadProfile()
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
            )
        } else {
            router.openLoginScreen()
        }
    }

    fun openLanguageScreen() {
        router.openLanguageScreen()
    }
}