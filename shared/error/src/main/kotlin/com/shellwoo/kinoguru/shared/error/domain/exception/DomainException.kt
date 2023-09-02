package com.shellwoo.kinoguru.shared.error.domain.exception

sealed class DomainException(
    override val message: String,
) : BaseException(message)

data class UnauthorizedException(
    override val message: String
) : DomainException(message)

data class NotFoundException(
    override val message: String
) : DomainException(message)

data class ServiceUnavailableException(
    override val message: String
) : DomainException(message)

data class InnerException(
    override val message: String
) : DomainException(message)
