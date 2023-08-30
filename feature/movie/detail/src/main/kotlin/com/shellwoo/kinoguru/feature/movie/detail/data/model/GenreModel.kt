package com.shellwoo.kinoguru.feature.movie.detail.data.model

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null
)