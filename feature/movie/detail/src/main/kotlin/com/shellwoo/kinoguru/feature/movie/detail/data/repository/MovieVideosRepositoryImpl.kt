package com.shellwoo.kinoguru.feature.movie.detail.data.repository

import com.shellwoo.kinoguru.feature.movie.detail.data.converter.MovieVideosResultModelConverter
import com.shellwoo.kinoguru.feature.movie.detail.data.network.MovieVideosApi
import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieVideosRepository
import com.shellwoo.kinoguru.shared.language.data.converter.LanguageTagConverter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class MovieVideosRepositoryImpl @Inject constructor(
    private val api: MovieVideosApi,
    private val movieVideosResultModelConverter: MovieVideosResultModelConverter,
    private val languageTagConverter: LanguageTagConverter,
) : MovieVideosRepository {

    override suspend fun getAll(id: Int, language: Language): List<MovieVideo> {
        val languageTag = languageTagConverter.toTag(language)
        val movieVideosResultModel = api.getAll(id, languageTag)
        return movieVideosResultModelConverter.toMovieVideos(movieVideosResultModel)
    }
}