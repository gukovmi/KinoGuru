package com.shellwoo.kinoguru.shared.error.domain.usecase

import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.repository.BaseExceptionRepository
import javax.inject.Inject

class GetBaseExceptionUseCase @Inject constructor(
    private val baseExceptionRepository: BaseExceptionRepository,
) : (Exception) -> BaseException by baseExceptionRepository::get