package com.shellwoo.kinoguru.shared.error.ui

import com.shellwoo.kinoguru.core.base.LocalizedContext
import com.shellwoo.kinoguru.shared.error.R
import javax.inject.Inject

class UnknownExceptionMessageConverter @Inject constructor(private val context: LocalizedContext) {

    fun toMessage(): String =
        context.getString(R.string.unknown_error_message)
}