package com.shellwoo.kinoguru.core.test.unit

import kotlinx.coroutines.awaitCancellation
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<T>.thenNeverAnswer() {
    doSuspendableAnswer { awaitCancellation() }
}