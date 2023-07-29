package com.shellwoo.kinoguru.feature.language.ui

import androidx.recyclerview.widget.DiffUtil
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class LanguageDiffUtilCallback @Inject constructor() :
    DiffUtil.ItemCallback<Language>() {

    override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean =
        newItem == oldItem

    override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean =
        oldItem == newItem
}