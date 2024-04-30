package com.shellwoo.kinoguru.feature.theme.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.ui.ext.inflate
import com.shellwoo.kinoguru.feature.theme.R
import com.shellwoo.kinoguru.feature.theme.databinding.ThemeItemBinding
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.ui.ThemeNameConverter

class ThemeViewHolder(
    private val themeNameConverter: ThemeNameConverter,
    private val themeIconConverter: ThemeIconConverter,
    private val onClickListener: ((Theme) -> Unit)?,
    parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.theme_item, false)) {

    private val binding by viewBinding(ThemeItemBinding::bind)

    fun bind(theme: Theme) {
        with(binding) {
            val themeName = themeNameConverter.toName(theme)
            name.text = themeName

            val themeIcon = themeIconConverter.toIcon(theme)
            icon.setImageDrawable(themeIcon)

            onClickListener?.let { itemView.setOnClickListener { it(theme) } }
        }
    }
}