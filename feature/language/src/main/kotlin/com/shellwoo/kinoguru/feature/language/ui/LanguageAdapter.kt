package com.shellwoo.kinoguru.feature.language.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class LanguageAdapter @Inject constructor(
    private val languageFlagConverter: LanguageFlagConverter,
    diffUtilCallback: LanguageDiffUtilCallback,
) : ListAdapter<Language, LanguageViewHolder>(diffUtilCallback) {

    private var onClickListener: ((Language) -> Unit)? = null

    fun setOnClickListener(listener: (Language) -> Unit) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder =
        LanguageViewHolder(languageFlagConverter, onClickListener, parent)

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}