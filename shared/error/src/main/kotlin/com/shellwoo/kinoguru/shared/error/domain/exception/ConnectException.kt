package com.shellwoo.kinoguru.shared.error.domain.exception

sealed class ConnectException(
    override val message: String
) : BaseException(message)

data class ServiceConnectException(
    override val message: String,
) : ConnectException(message)

data class ClientConnectException(
    override val message: String,
) : ConnectException(message)