package com.shellwoo.kinoguru.shared.language.domain.usecase

import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.repository.LanguageRepository
import javax.inject.Inject

class GetAllLanguagesUseCase @Inject constructor(
    private val repository: LanguageRepository
) : () -> List<Language> by repository::getAll