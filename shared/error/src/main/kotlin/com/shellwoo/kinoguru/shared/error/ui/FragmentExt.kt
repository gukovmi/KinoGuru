package com.shellwoo.kinoguru.shared.error.ui

import androidx.fragment.app.Fragment
import com.shellwoo.kinoguru.core.ui.ext.showOkDialog
import com.shellwoo.kinoguru.core.ui.ext.showRetryCancelDialog
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import com.shellwoo.kinoguru.shared.error.domain.exception.DomainException
import com.shellwoo.kinoguru.shared.error.domain.exception.UnknownException
import com.shellwoo.kinoguru.design.resource.R as designResourceR

fun Fragment.showErrorDialog(
    baseException: BaseException,
    retryAction: (() -> Unit),
    cancelAction: (() -> Unit)? = null,
    okAction: (() -> Unit)? = null,
) {
    val baseExceptionMessageConverter = BaseExceptionMessageConverter(requireContext())

    val errorMessage = baseExceptionMessageConverter.toMessage(baseException)
    val errorIconRes = designResourceR.drawable.error

    when (baseException) {
        is ConnectException -> showRetryCancelDialog(
            retryAction = retryAction,
            cancelAction = cancelAction,
            message = errorMessage,
            iconRes = errorIconRes,
        )

        is DomainException -> showDomainErrorDialog(
            domainException = baseException,
            retryAction = retryAction,
            cancelAction = cancelAction,
            okAction = okAction,
            errorMessage = errorMessage,
            errorIconRes = errorIconRes,
        )

        is UnknownException -> showOkDialog(
            okAction = okAction,
            message = errorMessage,
            iconRes = errorIconRes,
        )
    }
}

private fun Fragment.showDomainErrorDialog(
    domainException: DomainException,
    retryAction: (() -> Unit),
    cancelAction: (() -> Unit)? = null,
    okAction: (() -> Unit)? = null,
    errorMessage: String,
    errorIconRes: Int,
) {
    when (domainException) {
        is DomainException.UnauthorizedException,
        is DomainException.InnerException -> showOkDialog(
            okAction = okAction,
            message = errorMessage,
            iconRes = errorIconRes,
        )

        is DomainException.NotFoundException,
        is DomainException.ServiceUnavailableException -> showRetryCancelDialog(
            retryAction = retryAction,
            cancelAction = cancelAction,
            message = errorMessage,
            iconRes = errorIconRes,
        )
    }
}