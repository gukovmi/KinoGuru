package com.shellwoo.kinoguru.feature.movie.search

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

class SimpleRecognitionListener(private val onResult: (String) -> Unit) : RecognitionListener {

    override fun onResults(results: Bundle) {
        val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (matches != null) {
            onResult(matches[0])
        }
    }

    override fun onReadyForSpeech(params: Bundle) {}
    override fun onBeginningOfSpeech() {}
    override fun onRmsChanged(rmsdB: Float) {}
    override fun onBufferReceived(buffer: ByteArray) {}
    override fun onEndOfSpeech() {}
    override fun onError(error: Int) {}
    override fun onPartialResults(partialResults: Bundle) {}
    override fun onEvent(eventType: Int, params: Bundle) {}
}