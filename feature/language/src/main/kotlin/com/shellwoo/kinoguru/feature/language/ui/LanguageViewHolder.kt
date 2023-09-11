package com.shellwoo.kinoguru.feature.language.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shellwoo.kinoguru.core.ui.ext.inflate
import com.shellwoo.kinoguru.feature.language.R
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.ui.LanguageNameConverter
import kotlinx.android.synthetic.main.language_item.view.*

class LanguageViewHolder(
    private val languageNameConverter: LanguageNameConverter,
    private val languageFlagConverter: LanguageFlagConverter,
    private val onClickListener: ((Language) -> Unit)?,
    parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.language_item, false)) {

    fun bind(language: Language) {
        with(itemView) {
            val languageName = languageNameConverter.toName(language)
            name.text = languageName

            val languageFlag = languageFlagConverter.toFlag(language)
            flag.setImageDrawable(languageFlag)

            onClickListener?.let { itemView.setOnClickListener { it(language) } }
        }
    }
}