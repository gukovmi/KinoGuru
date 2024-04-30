package com.shellwoo.kinoguru.feature.theme.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.setResult
import com.shellwoo.kinoguru.feature.theme.R
import com.shellwoo.kinoguru.feature.theme.databinding.ThemeFragmentBinding
import com.shellwoo.kinoguru.feature.theme.di.ThemeComponentViewModel
import com.shellwoo.kinoguru.feature.theme.presentation.ThemeViewModel
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import com.shellwoo.kinoguru.shared.theme.ui.ThemeResultContract
import javax.inject.Inject

class ThemeFragment : BaseFragment(R.layout.theme_fragment) {

    private val componentViewModel: ThemeComponentViewModel by viewModels()
    private val viewModel: ThemeViewModel by viewModels(factoryProducer = { viewModelFactory })
    private val binding by viewBinding(ThemeFragmentBinding::bind)

    @Inject
    lateinit var themeAdapter: ThemeAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { viewModel.close() }
        themeAdapter.setOnClickListener(::selectTheme)
        viewModel.themes.observe(viewLifecycleOwner, ::renderThemes)
    }

    private fun selectTheme(theme: Theme) {
        setResult(ThemeResultContract, theme)
        viewModel.close()
    }

    private fun renderThemes(values: List<Theme>) {
        themeAdapter.submitList(values)
        binding.themes.adapter = themeAdapter
    }
}