package com.shellwoo.kinoguru.feature.theme.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.ui.ThemeNameConverter
import javax.inject.Inject

class ThemeAdapter @Inject constructor(
    private val themeNameConverter: ThemeNameConverter,
    private val themeIconConverter: ThemeIconConverter,
    diffUtilCallback: ThemeDiffUtilCallback,

    ) : ListAdapter<Theme, ThemeViewHolder>(diffUtilCallback) {

    private var onClickListener: ((Theme) -> Unit)? = null

    fun setOnClickListener(listener: (Theme) -> Unit) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder =
        ThemeViewHolder(themeNameConverter, themeIconConverter, onClickListener, parent)

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}