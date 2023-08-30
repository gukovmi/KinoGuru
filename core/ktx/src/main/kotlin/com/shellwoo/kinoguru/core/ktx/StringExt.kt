package com.shellwoo.kinoguru.core.ktx

import android.text.Spanned
import androidx.core.text.HtmlCompat

fun String.fromHtml(): Spanned =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun String?.replaceFromEmptyToNull(): String? =
    if (this?.isEmpty() == true) null else this