package com.shellwoo.kinoguru.shared.error.domain.exception

sealed class ConnectException : BaseException() {

    object ServiceConnectException : ConnectException()
    object ClientConnectException : ConnectException()
}