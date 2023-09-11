package com.shellwoo.kinoguru.core.base

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import java.util.*
import javax.inject.Inject

class LocalizedContext @Inject constructor(private val baseContext: Context) : ContextWrapper(baseContext) {

    override fun getResources(): Resources {
        val localeListCompat = AppCompatDelegate.getApplicationLocales()
        val tag = localeListCompat.toLanguageTags()
        val locale = Locale(tag)

        val newConfiguration = Configuration(baseContext.resources.configuration).apply { setLocale(locale) }

        return baseContext.createConfigurationContext(newConfiguration).resources
    }
}