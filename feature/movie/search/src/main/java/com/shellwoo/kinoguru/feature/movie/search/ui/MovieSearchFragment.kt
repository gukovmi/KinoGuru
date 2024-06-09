package com.shellwoo.kinoguru.feature.movie.search.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.MotionEvent
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shellwoo.kinoguru.core.ui.component.AfterTextWatcher
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.core.ui.ext.hideKeyboard
import com.shellwoo.kinoguru.feature.movie.search.R
import com.shellwoo.kinoguru.feature.movie.search.SimpleRecognitionListener
import com.shellwoo.kinoguru.feature.movie.search.databinding.MovieSearchFragmentBinding
import com.shellwoo.kinoguru.feature.movie.search.di.MovieSearchComponentViewModel
import com.shellwoo.kinoguru.feature.movie.search.presentation.MovieSearchViewModel
import com.shellwoo.kinoguru.feature.movie.search.presentation.ScreenState
import com.shellwoo.kinoguru.feature.movie.search.presentation.SearchState
import com.shellwoo.kinoguru.shared.error.domain.exception.BaseException
import com.shellwoo.kinoguru.shared.error.ui.BaseExceptionMessageConverter
import com.shellwoo.kinoguru.shared.error.ui.showErrorDialog
import com.shellwoo.kinoguru.shared.movie.ui.RatingFormatter
import com.shellwoo.kinoguru.shared.onboarding.ui.OnboardingDialogFragment
import com.shellwoo.kinoguru.shared.permission.ui.Permission
import com.shellwoo.kinoguru.shared.permission.ui.checkAndRequestPermission
import com.shellwoo.kinoguru.shared.permission.ui.isPermissionGranted
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import java.util.Locale
import javax.inject.Inject

class MovieSearchFragment : BaseFragment(R.layout.movie_search_fragment) {

    private companion object {

        const val RECOGNIZER_IMMEDIATELY_SPEECH_PROMPT = "Speak now"
    }

    @Inject
    lateinit var ratingFormatter: RatingFormatter

    @Inject
    lateinit var diffUtilCallback: MovieSearchItemDiffUtilCallback

    @Inject
    lateinit var baseExceptionMessageConverter: BaseExceptionMessageConverter

    private val componentViewModel: MovieSearchComponentViewModel by viewModels()
    private val viewModel: MovieSearchViewModel by viewModels(factoryProducer = ::viewModelFactory)
    private val binding by viewBinding(MovieSearchFragmentBinding::bind)

    private val moviesOnScrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == SCROLL_STATE_DRAGGING) hideKeyboard()
        }
    }
    private val speechRecognizer by lazy { SpeechRecognizer.createSpeechRecognizer(requireContext()) }
    private var speechRecognitionListener: RecognitionListener? = null
    private val microPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { viewModel.handleMicroPermissionResult(it) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initListeners()
        initSpeechRecognition()
        observeViewModel()

        if (savedInstanceState == null) {
            viewModel.start()
        }
    }

    private fun initRecycler() {
        binding.movies.adapter = MovieSearchItemAdapter(
            ratingFormatter,
            viewModel::selectMovieSuccessItem,
            diffUtilCallback
        )
        binding.movies.itemAnimator = ScaleInAnimator()
    }

    private fun initListeners() {
        binding.searchInput.addTextChangedListener(AfterTextWatcher(viewModel::setQuery))
        binding.movies.addOnScrollListener(moviesOnScrollListener)
    }

    private fun initSpeechRecognition() {
        speechRecognitionListener = SimpleRecognitionListener(binding.searchInput::setText)
        speechRecognizer.setRecognitionListener(speechRecognitionListener)
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.onboardingEvent.observe(viewLifecycleOwner) { renderOnboarding() }
        viewModel.searchErrorEvent.observe(viewLifecycleOwner, ::renderSearchError)
    }

    private fun renderState(state: ScreenState) {
        when (state) {
            ScreenState.Initial -> renderInitialState()
            is ScreenState.Content -> renderContentState(state)
        }
    }

    private fun renderInitialState() {
        binding.toolbar.isVisible = false
        binding.searchInput.isVisible = false
    }

    private fun renderContentState(state: ScreenState.Content) {
        binding.toolbar.isVisible = true
        binding.searchInput.isVisible = true
        renderSearchState(state.searchState)
        renderMicroState(state.microAvailable)
    }

    private fun renderSearchState(state: SearchState) {
        when (state) {
            SearchState.None -> renderNoneSearchState()
            is SearchState.Items -> renderSuccessSearchState(state)
            SearchState.NotFound -> renderNotFoundSearchState()
        }
    }

    private fun renderNoneSearchState() {
        binding.movies.isVisible = false
        binding.notFound.isVisible = false
    }

    private fun renderSuccessSearchState(state: SearchState.Items) {
        (binding.movies.adapter as MovieSearchItemAdapter).submitList(state.value)
        binding.movies.isVisible = true
        binding.notFound.isVisible = false
    }

    private fun renderNotFoundSearchState() {
        binding.movies.isVisible = false
        binding.notFound.isVisible = true
    }

    private fun renderMicroState(available: Boolean) {
        if (available) {
            renderMicroAvailableState()
        } else {
            renderMicroUnavailableState()
        }
    }

    private fun renderMicroAvailableState() {
        removeMicroListeners()

        with(binding.micro) {
            setImageResource(R.drawable.micro_on)
            setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startListening()
                        true
                    }

                    MotionEvent.ACTION_UP -> {
                        speechRecognizer.stopListening()
                        true
                    }
                }
                false
            }
        }
    }

    private fun renderMicroUnavailableState() {
        removeMicroListeners()

        with(binding.micro) {
            setImageResource(R.drawable.micro_off)
            setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        checkAndRequestPermission(
                            permission = Permission.MICRO,
                            permissionLauncher = microPermissionLauncher,
                            onPermissionResult = viewModel::handleMicroPermissionResult,
                        )
                        true
                    }
                }
                false
            }
        }
    }

    private fun removeMicroListeners() {
        binding.micro.setOnTouchListener(null)
    }

    private fun startListening() {
        val speechRecognitionIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            .putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            .putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            .putExtra(RecognizerIntent.EXTRA_PROMPT, RECOGNIZER_IMMEDIATELY_SPEECH_PROMPT)
        speechRecognizer.startListening(speechRecognitionIntent)
    }

    private fun renderOnboarding() {
        OnboardingDialogFragment.show(
            fragmentManager = childFragmentManager,
            targetView = binding.searchInputLayout,
            description = getString(R.string.movie_search_input_onboarding)
        )
    }

    private fun renderSearchError(baseException: BaseException) {
        showErrorDialog(
            baseExceptionMessageConverter = baseExceptionMessageConverter,
            baseException = baseException,
            retryAction = viewModel::search,
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleMicroPermissionResult(isPermissionGranted(Permission.MICRO))
    }

    override fun onDestroyView() {
        speechRecognitionListener = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        speechRecognizer.destroy()
        super.onDestroy()
    }
}