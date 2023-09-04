package com.shellwoo.kinoguru.shared.error.domain.exception

data class UnknownException(
    override val message: String
) : BaseException(message)