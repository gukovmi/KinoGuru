package com.shellwoo.kinoguru.feature.movie.detail.domain.scenario

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieDetails
import com.shellwoo.kinoguru.feature.movie.detail.domain.repository.MovieDetailsRepository
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import javax.inject.Inject

class GetMovieDetailsScenario @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
) {

    suspend operator fun invoke(id: Int): MovieDetails {
        val language = getCurrentLanguageUseCase()
        return movieDetailsRepository.get(id, language)
    }
}