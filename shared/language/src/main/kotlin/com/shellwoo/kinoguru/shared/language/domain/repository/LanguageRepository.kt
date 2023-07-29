package com.shellwoo.kinoguru.shared.language.domain.repository

import com.shellwoo.kinoguru.shared.language.domain.entity.Language

interface LanguageRepository {

    fun getAll(): List<Language>

    fun getCurrent(): Language

    fun setCurrent(language: Language)
}