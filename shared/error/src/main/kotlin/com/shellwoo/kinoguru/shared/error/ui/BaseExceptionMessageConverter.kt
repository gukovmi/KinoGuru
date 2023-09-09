package com.shellwoo.kinoguru.shared.error.ui

import android.content.Context
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import com.shellwoo.kinoguru.shared.error.domain.exception.DomainException
import com.shellwoo.kinoguru.shared.error.domain.exception.UnknownException

class BaseExceptionMessageConverter(context: Context) {

    private val connectExceptionMessageConverter = ConnectExceptionMessageConverter(context)
    private val domainExceptionMessageConverter = DomainExceptionMessageConverter(context)
    private val unknownExceptionMessageConverter = UnknownExceptionMessageConverter(context)

    fun toMessage(baseException: BaseException): String =
        when (baseException) {
            is ConnectException -> connectExceptionMessageConverter.toMessage(baseException)
            is DomainException -> domainExceptionMessageConverter.toMessage(baseException)
            UnknownException -> unknownExceptionMessageConverter.toMessage()
        }
}