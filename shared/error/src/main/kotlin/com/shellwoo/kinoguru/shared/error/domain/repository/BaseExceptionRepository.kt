package com.shellwoo.kinoguru.shared.error.domain.repository

import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException

interface BaseExceptionRepository {

    fun get(exception: Exception): BaseException
}