package com.shellwoo.kinoguru.feature.language.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.ui.ext.inflate
import com.shellwoo.kinoguru.feature.language.R
import com.shellwoo.kinoguru.feature.language.databinding.LanguageItemBinding
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.ui.LanguageNameConverter

class LanguageViewHolder(
    private val languageNameConverter: LanguageNameConverter,
    private val languageFlagConverter: LanguageFlagConverter,
    private val onClickListener: ((Language) -> Unit)?,
    parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.language_item, false)) {

    private val binding by viewBinding(LanguageItemBinding::bind)

    fun bind(language: Language) {
        with(binding) {
            val languageName = languageNameConverter.toName(language)
            name.text = languageName

            val languageFlag = languageFlagConverter.toFlag(language)
            flag.setImageDrawable(languageFlag)

            onClickListener?.let { root.setOnClickListener { it(language) } }
        }
    }
}