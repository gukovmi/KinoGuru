package com.shellwoo.kinoguru.core.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchTrying(
    errorHandler: ((Throwable) -> Unit),
    block: suspend CoroutineScope.() -> Unit,
): Job =
    launch(
        context = CoroutineExceptionHandler { _, throwable -> errorHandler(throwable) },
        block = block,
    )