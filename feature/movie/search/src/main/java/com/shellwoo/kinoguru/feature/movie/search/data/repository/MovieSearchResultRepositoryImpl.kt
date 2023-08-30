package com.shellwoo.kinoguru.feature.movie.search.data.repository

import com.shellwoo.kinoguru.feature.movie.search.data.converter.MovieSearchResultModelConverter
import com.shellwoo.kinoguru.feature.movie.search.data.network.MovieSearchApi
import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearchResult
import com.shellwoo.kinoguru.feature.movie.search.domain.repository.MovieSearchResultRepository
import com.shellwoo.kinoguru.shared.language.data.converter.LanguageTagConverter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class MovieSearchResultRepositoryImpl @Inject constructor(
    private val api: MovieSearchApi,
    private val movieSearchResultModelConverter: MovieSearchResultModelConverter,
    private val languageTagConverter: LanguageTagConverter,
) : MovieSearchResultRepository {

    override suspend fun get(query: String, language: Language, page: Int?): MovieSearchResult {
        val languageTag = languageTagConverter.toTag(language)
        val searchMovieResultModel = api.get(query, languageTag, page)
        return movieSearchResultModelConverter.from(searchMovieResultModel)
    }
}