package com.shellwoo.kinoguru.shared.error

import com.shellwoo.kinoguru.core.ktx.mapError
import com.shellwoo.kinoguru.shared.error.data.converter.ConnectExceptionConverter
import com.shellwoo.kinoguru.shared.error.data.converter.HttpCodeExceptionConverter
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class ErrorInterceptor @Inject constructor(
    private val httpCodeExceptionConverter: HttpCodeExceptionConverter,
    private val connectExceptionConverter: ConnectExceptionConverter,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        runCatching { chain.proceed(chain.request()) }
            .mapError(::handleError)
            .mapCatching(::handleHttpCode)
            .getOrThrow()

    private fun handleError(throwable: Throwable): BaseException {
        val exception = throwable as? Exception ?: throw throwable

        return connectExceptionConverter.convert(exception)
    }

    private fun handleHttpCode(response: Response): Response =
        response.takeIf { it.code == HTTP_OK }
            ?: throw httpCodeExceptionConverter.convert(response.code)
}