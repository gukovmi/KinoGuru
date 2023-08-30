package com.shellwoo.kinoguru.shared.movie.ui

import javax.inject.Inject

class RatingFormatter @Inject constructor() {

    private companion object {

        const val NOT_FOUND_DATA = "-"
        const val RATING_FORMAT_PATTERN = "%.2f"
    }

    fun format(value: Double?): String =
        value?.let { RATING_FORMAT_PATTERN.format(it) } ?: NOT_FOUND_DATA
}