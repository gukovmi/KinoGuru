package com.shellwoo.kinoguru.shared.error.ui

import com.shellwoo.kinoguru.core.base.LocalizedContext
import com.shellwoo.kinoguru.shared.error.R
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import javax.inject.Inject

class ConnectExceptionMessageConverter @Inject constructor(private val context: LocalizedContext) {

    fun toMessage(connectException: ConnectException): String =
        when (connectException) {
            ConnectException.ClientConnectException -> context.getString(R.string.client_connection_error_message)
            ConnectException.ServiceConnectException -> context.getString(R.string.service_connection_error_message)
        }
}