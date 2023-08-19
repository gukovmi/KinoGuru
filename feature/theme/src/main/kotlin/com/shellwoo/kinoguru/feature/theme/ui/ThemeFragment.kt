package com.shellwoo.kinoguru.feature.theme.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.setResult
import com.shellwoo.kinoguru.feature.theme.R
import com.shellwoo.kinoguru.feature.theme.di.ThemeComponentViewModel
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeViewModel
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.ui.ThemeResultContract
import kotlinx.android.synthetic.main.theme_fragment.*
import javax.inject.Inject

class ThemeFragment : BaseFragment(R.layout.theme_fragment) {

    private val componentViewModel: ThemeComponentViewModel by viewModels()
    private val viewModel: ThemeViewModel by viewModels(factoryProducer = { viewModelFactory })

    @Inject
    lateinit var themeAdapter: ThemeAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { viewModel.close() }
        themeAdapter.setOnClickListener(::selectTheme)
        viewModel.themes.observe(viewLifecycleOwner, ::renderThemes)
    }

    private fun selectTheme(theme: Theme) {
        setResult(ThemeResultContract, theme)
        viewModel.close()
    }

    private fun renderThemes(values: List<Theme>) {
        themeAdapter.submitList(values)
        themes.adapter = themeAdapter
    }
}