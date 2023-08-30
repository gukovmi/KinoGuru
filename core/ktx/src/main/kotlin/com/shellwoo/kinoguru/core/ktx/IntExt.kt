package com.shellwoo.kinoguru.core.ktx

fun Int?.replaceFromZeroToNull(): Int? =
    if (this == 0) null else this