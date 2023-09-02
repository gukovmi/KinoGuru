package com.shellwoo.kinoguru.shared.error.domain.exception

import java.io.IOException

sealed class BaseException(
    override val message: String
) : IOException(message)