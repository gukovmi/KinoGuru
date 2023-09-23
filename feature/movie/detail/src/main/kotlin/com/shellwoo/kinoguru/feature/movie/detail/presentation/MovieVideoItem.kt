package com.shellwoo.kinoguru.feature.movie.detail.presentation

sealed class MovieVideoItem(
    open val id: String,
    open val key: String,
    open val name: String,
) {

    data class YouTube(
        override val id: String,
        override val key: String,
        override val name: String,
    ) : MovieVideoItem(id, key, name)
}