package com.shellwoo.kinoguru.shared.error.data.converter

import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import java.net.NoRouteToHostException
import java.net.UnknownHostException
import javax.inject.Inject

class ConnectExceptionConverter @Inject constructor() {

    fun convert(exception: Exception): ConnectException =
        when (exception) {
            is ConnectException,
            is NoRouteToHostException,
            is UnknownHostException -> ConnectException.ClientConnectException

            else -> ConnectException.ServiceConnectException
        }.apply { addSuppressed(exception) }
}