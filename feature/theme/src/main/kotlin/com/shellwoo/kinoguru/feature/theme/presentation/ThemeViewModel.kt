package com.shellwoo.kinoguru.feature.theme.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.domain.usecase.GetAvailableThemesUseCase
import javax.inject.Inject

class ThemeViewModel @Inject constructor(
    getAvailableThemesUseCase: GetAvailableThemesUseCase,
    private val router: ThemeRouter
) : ViewModel() {

    private val _themes = MutableLiveData(getAvailableThemesUseCase())
    val themes: LiveData<List<Theme>> = _themes

    fun close() {
        router.close()
    }
}