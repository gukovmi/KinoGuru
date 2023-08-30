package com.shellwoo.kinoguru.core.ktx

fun Double?.replaceFromZeroToNull(): Double? =
    if (this == 0.0) null else this