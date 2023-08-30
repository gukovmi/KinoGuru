package com.shellwoo.kinoguru.feature.movie.detail.data.converter

import com.shellwoo.kinoguru.feature.movie.detail.data.model.GenreModel
import com.shellwoo.kinoguru.feature.movie.detail.data.model.MovieDetailsModel
import com.shellwoo.kinoguru.feature.movie.detail.data.model.ProductionCompanyModel
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieDetailsModelConverterTest {

    private val converter = MovieDetailsModelConverter()

    @Test
    fun `to entity EXPECT movie details`() {
        val backdropPath = "backdropPath"
        val budget = 100000
        val genres = ArrayList<GenreModel>()
        val id = 123
        val overview = "overview"
        val posterPath = "posterPath"
        val productionCompanies = ArrayList<ProductionCompanyModel>()
        val releaseDate = "11.11.2011"
        val revenue = 200000
        val title = "title"
        val voteAverage = 9.55
        val model = MovieDetailsModel(
            adult = false,
            backdropPath = backdropPath,
            budget = budget,
            genres = genres,
            id = id,
            originalLanguage = "Russian",
            originalTitle = "title",
            overview = overview,
            posterPath = posterPath,
            productionCompanies = productionCompanies,
            productionCountries = ArrayList(),
            releaseDate = releaseDate,
            revenue = revenue,
            title = title,
            voteAverage = voteAverage,
            voteCount = 1234,
            belongsToCollection = null,
            homepage = null,
            imdbId = null,
            popularity = null,
            runtime = null,
            spokenLanguages = ArrayList(),
            status = null,
            tagline = null,
            video = null,
        )
        val expected = MovieDetails(
            backdropPath = backdropPath,
            budget = budget,
            genres = genres,
            id = id,
            overview = overview,
            posterPath = posterPath,
            productionCompanies = productionCompanies,
            releaseDate = releaseDate,
            revenue = revenue,
            title = title,
            voteAverage = voteAverage,
        )

        val actual = converter.toEntity(model)

        assertEquals(expected, actual)
    }
}