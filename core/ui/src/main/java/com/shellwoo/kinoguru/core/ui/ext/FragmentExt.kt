package com.shellwoo.kinoguru.core.ui.ext

import android.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.shellwoo.kinoguru.core.ui.FragmentResultContract
import com.shellwoo.kinoguru.core.ui.R
import java.io.Serializable

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun Fragment.showRetryCancelDialog(
    retryAction: (() -> Unit),
    cancelAction: (() -> Unit)? = null,
    message: String,
    @DrawableRes iconRes: Int,
) {
    AlertDialog.Builder(requireContext())
        .setPositiveButton(R.string.dialog_button_retry) { _, _ -> retryAction() }
        .setNegativeButton(R.string.dialog_button_cancel) { _, _ -> cancelAction?.invoke() }
        .setOnDismissListener { cancelAction?.invoke() }
        .setTitle(message)
        .setIcon(iconRes)
        .setIconAttribute(android.R.attr.alertDialogIcon)
        .create()
        .show()
}

fun Fragment.showOkDialog(
    okAction: (() -> Unit)? = null,
    message: String,
    @DrawableRes iconRes: Int,
) {
    AlertDialog.Builder(requireContext())
        .setPositiveButton(R.string.dialog_button_ok) { _, _ -> okAction?.invoke() }
        .setOnDismissListener { okAction?.invoke() }
        .setTitle(message)
        .setIcon(iconRes)
        .setIconAttribute(android.R.attr.alertDialogIcon)
        .create()
        .show()
}

fun <T : Serializable> Fragment.setResult(contract: FragmentResultContract<T>, result: T) {
    val bundle = contract.fromResult(result)
    setFragmentResult(contract.key, bundle)
}

fun <T : Serializable> Fragment.setResultListener(contract: FragmentResultContract<T>, listener: (T) -> Unit) {
    setFragmentResultListener(contract.key) { _, bundle ->
        val result = bundle.getSerializable(contract.key) as T
        listener(result)
    }
}

fun Fragment.hideKeyboard() {
    val inputMethodManager = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
    inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
}