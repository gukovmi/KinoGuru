package com.shellwoo.kinoguru.shared.error.data.converter

import android.content.Context
import com.shellwoo.kinoguru.shared.error.R
import com.shellwoo.kinoguru.shared.error.domain.exception.ClientConnectException
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import com.shellwoo.kinoguru.shared.error.domain.exception.ServiceConnectException
import java.net.NoRouteToHostException
import java.net.UnknownHostException
import javax.inject.Inject

class ConnectExceptionConverter @Inject constructor(private val context: Context) {

    fun convert(exception: Exception): ConnectException =
        when (exception) {
            is ConnectException,
            is NoRouteToHostException,
            is UnknownHostException -> ClientConnectException(context.getString(R.string.client_connection_error_message))

            else -> ServiceConnectException(context.getString(R.string.service_connection_error_message))
        }.apply { addSuppressed(exception) }
}