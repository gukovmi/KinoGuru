package com.shellwoo.kinoguru.feature.movie.detail.data.repository

import com.shellwoo.kinoguru.feature.movie.detail.data.converter.MovieDetailsModelConverter
import com.shellwoo.kinoguru.feature.movie.detail.data.network.MovieDetailsApi
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieDetailsRepository
import com.shellwoo.kinoguru.shared.language.data.converter.LanguageTagConverter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: MovieDetailsApi,
    private val movieDetailsModelConverter: MovieDetailsModelConverter,
    private val languageTagConverter: LanguageTagConverter
) : MovieDetailsRepository {

    override suspend fun get(id: Int, language: Language): MovieDetails {
        val languageTag = languageTagConverter.toTag(language)
        val movieDetailsModel = api.get(id, languageTag)
        return movieDetailsModelConverter.toEntity(movieDetailsModel)
    }
}