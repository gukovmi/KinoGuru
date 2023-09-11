package com.shellwoo.kinoguru.shared.error.ui

import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import com.shellwoo.kinoguru.shared.error.domain.exception.DomainException
import com.shellwoo.kinoguru.shared.error.domain.exception.UnknownException
import javax.inject.Inject

class BaseExceptionMessageConverter @Inject constructor(
    private val connectExceptionMessageConverter: ConnectExceptionMessageConverter,
    private val domainExceptionMessageConverter: DomainExceptionMessageConverter,
    private val unknownExceptionMessageConverter: UnknownExceptionMessageConverter,
) {

    fun toMessage(baseException: BaseException): String =
        when (baseException) {
            is ConnectException -> connectExceptionMessageConverter.toMessage(baseException)
            is DomainException -> domainExceptionMessageConverter.toMessage(baseException)
            UnknownException -> unknownExceptionMessageConverter.toMessage()
        }
}