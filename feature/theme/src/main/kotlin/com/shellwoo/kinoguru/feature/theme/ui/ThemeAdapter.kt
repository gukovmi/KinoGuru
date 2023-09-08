package com.shellwoo.kinoguru.feature.theme.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import javax.inject.Inject

class ThemeAdapter @Inject constructor(
    private val themeIconConverter: ThemeIconConverter,
    diffUtilCallback: ThemeDiffUtilCallback,

    ) : ListAdapter<Theme, ThemeViewHolder>(diffUtilCallback) {

    private var onClickListener: ((Theme) -> Unit)? = null

    fun setOnClickListener(listener: (Theme) -> Unit) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder =
        ThemeViewHolder(themeIconConverter, onClickListener, parent)

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}