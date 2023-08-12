package com.shellwoo.kinoguru.shared.theme.domain.usecase

import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import javax.inject.Inject

class GetCurrentThemeUseCase @Inject constructor(repository: ThemeRepository) : () -> Theme by repository::get