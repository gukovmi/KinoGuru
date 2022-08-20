package com.shellwoo.kinoguru.feature.login.presentation

import android.app.PendingIntent
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import com.shellwoo.kinoguru.feature.login.domain.usecase.GetGoogleOneTapSignInIntentUseCase
import com.shellwoo.kinoguru.feature.login.domain.usecase.GetGoogleStandardSignInIntentUseCase
import com.shellwoo.kinoguru.feature.login.domain.usecase.GoogleSignInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getGoogleOneTapSignInIntentUseCase: GetGoogleOneTapSignInIntentUseCase,
    private val getGoogleStandardSignInIntentUseCase: GetGoogleStandardSignInIntentUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase,
) : ViewModel() {

    private val _requestSignInEvent = SingleLiveEvent<PendingIntent>()
    val requestSignInEvent: LiveData<PendingIntent> = _requestSignInEvent

    fun requestGoogleOneTapSignIn() {
        viewModelScope.launch {
            _requestSignInEvent(getGoogleOneTapSignInIntentUseCase())
        }
    }

    fun requestGoogleStandardSignIn() {
        viewModelScope.launch {
            _requestSignInEvent(getGoogleStandardSignInIntentUseCase())
        }
    }

    fun handleRequestSignInResult(intent: Intent?) {
        viewModelScope.launch { googleSignInUseCase(intent) }
    }
}