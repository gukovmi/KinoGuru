package com.shellwoo.kinoguru.feature.movie.search.domain.scenario

import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearchResult
import com.shellwoo.kinoguru.feature.movie.search.domain.repository.MovieSearchResultRepository
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import javax.inject.Inject

class GetMovieSearchResultScenario @Inject constructor(
    private val movieSearchResultRepository: MovieSearchResultRepository,
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
) {

    suspend operator fun invoke(query: String, page: Int? = null): MovieSearchResult {
        val language = getCurrentLanguageUseCase()
        return movieSearchResultRepository.get(query, language, page)
    }
}