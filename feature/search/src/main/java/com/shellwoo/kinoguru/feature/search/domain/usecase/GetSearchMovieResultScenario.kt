package com.shellwoo.kinoguru.feature.search.domain.usecase

import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovieResult
import com.shellwoo.kinoguru.feature.search.domain.repository.SearchMovieResultRepository
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import javax.inject.Inject

class GetSearchMovieResultScenario @Inject constructor(
    private val searchMovieResultRepository: SearchMovieResultRepository,
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
) {

    suspend operator fun invoke(query: String, page: Int? = null): SearchMovieResult {
        val language = getCurrentLanguageUseCase()
        return searchMovieResultRepository.get(query, language, page)
    }
}