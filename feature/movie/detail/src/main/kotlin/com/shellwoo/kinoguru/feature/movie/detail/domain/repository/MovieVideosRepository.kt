package com.shellwoo.kinoguru.feature.movie.detail.domain.repository

import com.shellwoo.kinoguru.feature.movie.detail.domain.entity.MovieVideo
import com.shellwoo.kinoguru.shared.language.domain.entity.Language

interface MovieVideosRepository {

    suspend fun getAll(id: Int, language: Language): List<MovieVideo>
}