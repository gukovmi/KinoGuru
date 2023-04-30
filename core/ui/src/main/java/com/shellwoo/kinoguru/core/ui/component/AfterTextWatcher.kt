package com.shellwoo.kinoguru.core.ui.component

import android.text.Editable
import android.text.TextWatcher

class AfterTextWatcher(private val onAfterTextChanged: (String) -> Unit) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        p0?.toString()?.let { onAfterTextChanged(it) }
    }
}