package com.shellwoo.kinoguru.shared.theme.domain.usecase

import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import javax.inject.Inject

class GetAvailableThemesUseCase @Inject constructor(private val repository: ThemeRepository) {

    operator fun invoke(): List<Theme> =
        repository.getAll().minus(Theme.UNSPECIFIED)
}