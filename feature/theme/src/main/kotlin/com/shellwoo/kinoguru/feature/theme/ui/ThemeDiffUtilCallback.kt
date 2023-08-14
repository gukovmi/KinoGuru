package com.shellwoo.kinoguru.feature.theme.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import javax.inject.Inject

class ThemeDiffUtilCallback @Inject constructor() :
    DiffUtil.ItemCallback<Theme>() {

    override fun areItemsTheSame(oldItem: Theme, newItem: Theme): Boolean =
        newItem == oldItem

    override fun areContentsTheSame(oldItem: Theme, newItem: Theme): Boolean =
        oldItem == newItem
}