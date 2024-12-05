package com.shellwoo.kinoguru.feature.theme.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.presentation.observeAsNotNullableState
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.component.NO_CONTENT_LAYOUT_ID
import com.shellwoo.kinoguru.core.ui.ext.createComposeView
import com.shellwoo.kinoguru.core.ui.ext.setResult
import com.shellwoo.kinoguru.feature.theme.di.ThemeComponentViewModel
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeViewModel
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.ui.ThemeNameConverter
import com.shellwoo.kinoguru.shared.theme.ui.ThemeResultContract
import javax.inject.Inject

class ThemeFragment : BaseFragment(NO_CONTENT_LAYOUT_ID) {

    private val componentViewModel: ThemeComponentViewModel by viewModels()
    private val viewModel: ThemeViewModel by viewModels(factoryProducer = { viewModelFactory })

    @Inject
    lateinit var themeNameConverter: ThemeNameConverter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        createComposeView {
            val themes by viewModel.themes.observeAsNotNullableState()

            ThemeScreen(
                themes = themes,
                themeNameConverter = themeNameConverter::toName,
                onThemeClick = ::selectTheme,
                onNavigationIconClick = viewModel::close,
            )
        }

    private fun selectTheme(theme: Theme) {
        setResult(ThemeResultContract, theme)
        viewModel.close()
    }
}