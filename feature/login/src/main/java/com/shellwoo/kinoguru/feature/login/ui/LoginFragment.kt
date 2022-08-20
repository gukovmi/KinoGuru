package com.shellwoo.kinoguru.feature.login.ui

import android.app.PendingIntent
import android.os.Bundle
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.identity.SignInClient
import com.shellwoo.kinoguru.core.ui.component.BaseFragment
import com.shellwoo.kinoguru.feature.login.R
import com.shellwoo.kinoguru.feature.login.presentation.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    @Inject
    lateinit var signInClient: SignInClient

    private val viewModel: LoginViewModel by viewModels(factoryProducer = ::viewModelFactory)

    private val requestSignInLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
        viewModel.handleRequestSignInResult(activityResult.data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        googleSignIn.setOnClickListener { viewModel.requestGoogleStandardSignIn() }
        viewModel.requestSignInEvent.observe(viewLifecycleOwner, ::requestSignIn)

        viewModel.requestGoogleOneTapSignIn()
    }

    private fun requestSignIn(pendingIntent: PendingIntent) {
        val intentSenderRequest = IntentSenderRequest.Builder(pendingIntent).build()
        requestSignInLauncher.launch(intentSenderRequest)
    }
}