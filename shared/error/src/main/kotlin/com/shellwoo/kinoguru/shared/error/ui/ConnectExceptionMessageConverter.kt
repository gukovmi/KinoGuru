package com.shellwoo.kinoguru.shared.error.ui

import android.content.Context
import com.shellwoo.kinoguru.shared.error.R
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException

class ConnectExceptionMessageConverter(private val context: Context) {

    fun toMessage(connectException: ConnectException): String =
        when (connectException) {
            ConnectException.ClientConnectException -> context.getString(R.string.client_connection_error_message)
            ConnectException.ServiceConnectException -> context.getString(R.string.service_connection_error_message)
        }
}