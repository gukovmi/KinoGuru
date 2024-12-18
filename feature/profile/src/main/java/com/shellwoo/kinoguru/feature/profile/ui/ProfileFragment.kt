package com.shellwoo.kinoguru.feature.profile.ui

import android.content.Context
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import com.shellwoo.kinoguru.core.presentation.observeAsNotNullableState
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.component.NO_CONTENT_LAYOUT_ID
import com.shellwoo.kinoguru.core.ui.ext.createComposeView
import com.shellwoo.kinoguru.core.ui.ext.setResultListener
import com.shellwoo.kinoguru.feature.profile.di.ProfileComponentViewModel
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileViewModel
import com.shellwoo.kinoguru.shared.language.ui.LanguageNameConverter
import com.shellwoo.kinoguru.shared.language.ui.LanguageResultContract
import com.shellwoo.kinoguru.shared.theme.ui.ThemeNameConverter
import com.shellwoo.kinoguru.shared.theme.ui.ThemeResultContract
import javax.inject.Inject

class ProfileFragment : BaseFragment(NO_CONTENT_LAYOUT_ID) {

    private val componentViewModel: ProfileComponentViewModel by viewModels()

    private val viewModel: ProfileViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val pickPhotoLauncher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            context?.contentResolver?.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION)
            viewModel.updateUserPhoto(uri.toString())
        }
    }

    @Inject
    lateinit var languageNameConverter: LanguageNameConverter

    @Inject
    lateinit var themeNameConverter: ThemeNameConverter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        createComposeView {
            val state by viewModel.state.observeAsNotNullableState()

            ProfileScreen(
                state = state,
                languageNameConverter = languageNameConverter::toName,
                themeNameConverter = themeNameConverter::toName,
                onLanguageClick = viewModel::openLanguageScreen,
                onThemeClick = viewModel::openThemeScreen,
                onPhotoClick = { pickPhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        if (savedInstanceState == null) {
            viewModel.loadProfile()
        }
    }

    private fun initListeners() {
        setResultListener(LanguageResultContract, viewModel::selectLanguage)
        setResultListener(ThemeResultContract, viewModel::selectTheme)
    }
}