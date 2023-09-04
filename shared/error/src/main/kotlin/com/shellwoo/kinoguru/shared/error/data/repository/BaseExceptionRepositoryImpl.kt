package com.shellwoo.kinoguru.shared.error.data.repository

import android.content.Context
import com.shellwoo.kinoguru.shared.error.R
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.domain.exception.UnknownException
import com.shellwoo.kinoguru.shared.error.domain.repository.BaseExceptionRepository
import javax.inject.Inject

class BaseExceptionRepositoryImpl @Inject constructor(
    private val context: Context,
) : BaseExceptionRepository {

    override fun get(exception: Exception): BaseException =
        when (exception) {
            is BaseException -> exception
            else -> UnknownException(context.getString(R.string.unknown_error_message))
        }
}