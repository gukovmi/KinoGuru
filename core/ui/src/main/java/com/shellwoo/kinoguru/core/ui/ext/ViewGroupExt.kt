package com.shellwoo.kinoguru.core.ui.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes res: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(res, this, attachToRoot)