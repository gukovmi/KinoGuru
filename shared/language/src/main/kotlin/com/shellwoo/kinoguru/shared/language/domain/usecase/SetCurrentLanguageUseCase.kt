package com.shellwoo.kinoguru.shared.language.domain.usecase

import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.repository.LanguageRepository
import javax.inject.Inject

class SetCurrentLanguageUseCase @Inject constructor(
    private val repository: LanguageRepository
) : (Language) -> Unit by repository::setCurrent