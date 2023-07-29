package com.shellwoo.kinoguru.feature.profile.domain.scenario

import com.shellwoo.kinoguru.feature.profile.domain.entity.Profile
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetProfileScenario @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
) {

    operator fun invoke(): Profile? {
        val user = getCurrentUserUseCase() ?: return null
        val language = getCurrentLanguageUseCase()
        return Profile(
            name = user.name,
            email = user.email,
            photoUrl = user.photoUrl,
            language = language,
        )
    }
}