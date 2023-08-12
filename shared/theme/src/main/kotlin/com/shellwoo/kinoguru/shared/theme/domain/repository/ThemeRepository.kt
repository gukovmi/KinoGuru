package com.shellwoo.kinoguru.shared.theme.domain.repository

import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme

interface ThemeRepository {

    suspend fun init()

    fun get(): Theme

    fun getAll(): List<Theme>

    suspend fun set(theme: Theme)
}