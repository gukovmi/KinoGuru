package com.shellwoo.kinoguru.shared.theme.domain.usecase

import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import javax.inject.Inject

class InitThemeUseCase @Inject constructor(repository: ThemeRepository) : suspend () -> Unit by repository::init