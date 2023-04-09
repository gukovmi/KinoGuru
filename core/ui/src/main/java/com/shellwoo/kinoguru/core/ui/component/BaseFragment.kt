package com.shellwoo.kinoguru.core.ui.component

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.shellwoo.kinoguru.core.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
}