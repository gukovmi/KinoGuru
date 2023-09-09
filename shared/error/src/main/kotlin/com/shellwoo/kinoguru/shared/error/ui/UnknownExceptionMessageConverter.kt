package com.shellwoo.kinoguru.shared.error.ui

import android.content.Context
import com.shellwoo.kinoguru.shared.error.R

class UnknownExceptionMessageConverter(private val context: Context) {

    fun toMessage(): String =
        context.getString(R.string.unknown_error_message)
}