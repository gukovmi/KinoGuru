package com.shellwoo.kinoguru.shared.error.data.repository

import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.exception.UnknownException
import com.shellwoo.kinoguru.shared.error.domain.repository.BaseExceptionRepository
import javax.inject.Inject

class BaseExceptionRepositoryImpl @Inject constructor() : BaseExceptionRepository {

    override fun get(exception: Exception): BaseException =
        when (exception) {
            is BaseException -> exception
            else -> UnknownException
        }
}