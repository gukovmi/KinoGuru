package com.shellwoo.kinoguru.shared.error.data.converter

import com.shellwoo.kinoguru.shared.error.domain.exception.DomainException
import java.net.HttpURLConnection.*
import javax.inject.Inject

class HttpCodeExceptionConverter @Inject constructor() {

    fun convert(code: Int): DomainException =
        when (code) {
            HTTP_UNAUTHORIZED -> DomainException.UnauthorizedException

            HTTP_NOT_FOUND -> DomainException.NotFoundException

            HTTP_GATEWAY_TIMEOUT,
            HTTP_UNAVAILABLE -> DomainException.ServiceUnavailableException

            else -> DomainException.InnerException
        }
}