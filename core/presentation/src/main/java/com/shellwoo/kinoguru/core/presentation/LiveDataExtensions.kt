package com.shellwoo.kinoguru.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState

import androidx.lifecycle.LiveData

@Composable
fun <T> LiveData<T>.observeAsNotNullableState(): State<T> {
    val notNullableValue = requireNotNull(value)
    return observeAsState(notNullableValue)
}