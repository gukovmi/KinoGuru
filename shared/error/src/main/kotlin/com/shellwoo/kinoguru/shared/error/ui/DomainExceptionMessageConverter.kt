package com.shellwoo.kinoguru.shared.error.ui

import com.shellwoo.kinoguru.core.base.LocalizedContext
import com.shellwoo.kinoguru.shared.error.R
import com.shellwoo.kinoguru.shared.error.domain.exception.DomainException
import javax.inject.Inject

class DomainExceptionMessageConverter @Inject constructor(private val context: LocalizedContext) {

    fun toMessage(domainException: DomainException): String =
        when (domainException) {
            DomainException.InnerException -> context.getString(R.string.inner_error_message)
            DomainException.NotFoundException -> context.getString(R.string.not_found_error_message)
            DomainException.ServiceUnavailableException -> context.getString(R.string.service_unavailable_error_message)
            DomainException.UnauthorizedException -> context.getString(R.string.unauthorized_error_message)
        }
}