package com.shellwoo.kinoguru.core.ui.component

import androidx.annotation.LayoutRes
import com.shellwoo.kinoguru.core.presentation.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : DaggerFragment(contentLayoutId) {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory
}