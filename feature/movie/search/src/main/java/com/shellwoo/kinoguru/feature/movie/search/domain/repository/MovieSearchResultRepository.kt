package com.shellwoo.kinoguru.feature.movie.search.domain.repository

import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearchResult
import com.shellwoo.kinoguru.shared.language.domain.entity.Language

interface MovieSearchResultRepository {

    suspend fun get(query: String, language: Language, page: Int?): MovieSearchResult
}