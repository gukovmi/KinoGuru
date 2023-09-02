package com.shellwoo.kinoguru.shared.error.data.converter

import android.content.Context
import com.shellwoo.kinoguru.shared.error.R
import com.shellwoo.kinoguru.shared.error.domain.exception.*
import java.net.HttpURLConnection.*
import javax.inject.Inject

class HttpCodeExceptionConverter @Inject constructor(private val context: Context) {

    fun convert(code: Int): DomainException =
        when (code) {
            HTTP_UNAUTHORIZED -> UnauthorizedException(context.getString(R.string.unauthorized_error_message))

            HTTP_NOT_FOUND -> NotFoundException(context.getString(R.string.not_found_error_message))

            HTTP_GATEWAY_TIMEOUT,
            HTTP_UNAVAILABLE -> ServiceUnavailableException(context.getString(R.string.service_unavailable_error_message))

            else -> InnerException(context.getString(R.string.inner_error_message))
        }
}