package com.shellwoo.kinoguru.feature.profile.ui

import android.content.Context
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.setResultListener
import com.shellwoo.kinoguru.feature.profile.R
import com.shellwoo.kinoguru.feature.profile.databinding.ProfileFragmentBinding
import com.shellwoo.kinoguru.feature.profile.di.ProfileComponentViewModel
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileState
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileViewModel
import com.shellwoo.kinoguru.shared.language.ui.LanguageNameConverter
import com.shellwoo.kinoguru.shared.language.ui.LanguageResultContract
import com.shellwoo.kinoguru.shared.theme.ui.ThemeNameConverter
import com.shellwoo.kinoguru.shared.theme.ui.ThemeResultContract
import javax.inject.Inject

class ProfileFragment : BaseFragment(R.layout.profile_fragment) {

    private val componentViewModel: ProfileComponentViewModel by viewModels()

    private val viewModel: ProfileViewModel by viewModels(factoryProducer = ::viewModelFactory)
    private val binding by viewBinding(ProfileFragmentBinding::bind)

    private val requestManager: RequestManager by lazy { Glide.with(this) }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        if (savedInstanceState == null) {
            viewModel.loadProfile()
        }
    }

    private fun initListeners() {
        setResultListener(LanguageResultContract, viewModel::selectLanguage)
        setResultListener(ThemeResultContract, viewModel::selectTheme)
        with(binding) {
            language.setOnClickListener { viewModel.openLanguageScreen() }
            photo.setOnClickListener { pickPhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
            theme.setOnClickListener { viewModel.openThemeScreen() }
        }
    }

    private fun renderState(state: ProfileState) {
        when (state) {
            ProfileState.Initial -> renderInitialState()
            ProfileState.Loading -> renderLoadingState()
            is ProfileState.Content -> renderContentState(state)
        }
    }

    private fun renderInitialState() {
        with(binding) {
            toolbar.isVisible = false
            content.isVisible = false
            progressBar.isVisible = false
        }
    }

    private fun renderLoadingState() {
        with(binding) {
            toolbar.isVisible = true
            content.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun renderContentState(state: ProfileState.Content) {
        with(binding) {
            toolbar.isVisible = true
            content.isVisible = true
            progressBar.isVisible = false

            state.name?.let { name.setText(it) }
            state.email?.let { email.setText(it) }
            state.photoUrl?.toUri()?.let { photoUri ->
                requestManager.load(photoUri)
                    .error(R.drawable.person)
                    .into(photo)
            } ?: photo.setImageResource(R.drawable.person)

            language.setText(languageNameConverter.toName(state.language))
            theme.setText(themeNameConverter.toName(state.theme))
        }
    }
}