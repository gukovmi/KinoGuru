package com.shellwoo.kinoguru.feature.search.data.repository

import com.shellwoo.kinoguru.feature.search.data.converter.SearchMovieResultModelConverter
import com.shellwoo.kinoguru.feature.search.data.network.SearchMovieApi
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.feature.search.domain.repository.SearchMovieResultRepository
import com.shellwoo.kinoguru.shared.language.data.converter.LanguageTagConverter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class SearchMovieResultRepositoryImpl @Inject constructor(
    private val api: SearchMovieApi,
    private val searchMovieResultModelConverter: SearchMovieResultModelConverter,
    private val languageTagConverter: LanguageTagConverter,
) : SearchMovieResultRepository {

    override suspend fun get(query: String, language: Language, page: Int?): SearchMovieResult {
        val languageTag = languageTagConverter.toTag(language)
        val searchMovieResultModel = api.get(query, languageTag, page)
        return searchMovieResultModelConverter.from(searchMovieResultModel)
    }
}