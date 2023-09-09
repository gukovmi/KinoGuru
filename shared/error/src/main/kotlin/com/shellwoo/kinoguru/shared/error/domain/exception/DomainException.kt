package com.shellwoo.kinoguru.shared.error.domain.exception

sealed class DomainException : BaseException() {

    object UnauthorizedException : DomainException()
    object NotFoundException : DomainException()
    object ServiceUnavailableException : DomainException()
    object InnerException : DomainException()
}