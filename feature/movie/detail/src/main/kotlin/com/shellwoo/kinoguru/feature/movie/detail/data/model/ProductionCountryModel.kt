package com.shellwoo.kinoguru.feature.movie.detail.data.model

import com.google.gson.annotations.SerializedName

data class ProductionCountryModel(
    @SerializedName("iso_3166_1") val iso31661: String?,
    @SerializedName("name") val name: String?,
)