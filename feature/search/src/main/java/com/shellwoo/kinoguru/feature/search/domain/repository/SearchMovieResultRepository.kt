package com.shellwoo.kinoguru.feature.search.domain.repository

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.shared.language.domain.entity.Language

interface SearchMovieResultRepository {

    suspend fun get(query: String, language: Language, page: Int?): SearchMovieResult
}