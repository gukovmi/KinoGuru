package com.shellwoo.kinoguru.shared.theme.data.repository

import com.shellwoo.kinoguru.shared.theme.data.datasource.ThemeLocalDataSource
import com.shellwoo.kinoguru.shared.theme.data.datasource.ThemeSystemDataSource
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val systemDataSource: ThemeSystemDataSource,
    private val localDataSource: ThemeLocalDataSource,
) : ThemeRepository {

    override suspend fun init() {
        val localTheme = localDataSource.get()
        if (localTheme == null) {
            val systemTheme = systemDataSource.get()
            localDataSource.set(systemTheme)
        } else {
            systemDataSource.set(localTheme)
        }
    }

    override fun get(): Theme =
        systemDataSource.get()

    override fun getAll(): List<Theme> =
        Theme.values().toList()

    override suspend fun set(theme: Theme) {
        localDataSource.set(theme)
        systemDataSource.set(theme)
    }
}