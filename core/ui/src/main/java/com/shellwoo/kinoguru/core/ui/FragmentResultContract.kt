package com.shellwoo.kinoguru.core.ui

import android.os.Bundle
import java.io.Serializable

interface FragmentResultContract<T : Serializable> {

    val key: String

    fun <T : Serializable> toResult(bundle: Bundle): T =
        bundle.getSerializable(key) as T

    fun <T : Serializable> fromResult(value: T): Bundle =
        Bundle().apply { putSerializable(key, value) }
}