package com.shellwoo.kinoguru.shared.theme.domain.usecase

import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import javax.inject.Inject

class SetCurrentThemeUseCase @Inject constructor(repository: ThemeRepository) : suspend (Theme) -> Unit by repository::set