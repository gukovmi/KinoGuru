package com.shellwoo.kinoguru.core.ui

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun Fragment.showRetryCancelErrorDialog(
    onRetryAction: (() -> Unit)? = null,
    onCancelAction: (() -> Unit)? = null,
) {
    AlertDialog.Builder(requireContext())
        .setPositiveButton(R.string.error_dialog_button_retry) { _, _ -> onRetryAction?.invoke() }
        .setNegativeButton(R.string.error_dialog_button_cancel) { _, _ -> onCancelAction?.invoke() }
        .setOnDismissListener { onCancelAction?.invoke() }
        .setTitle(R.string.error_dialog_title_repeatable)
        .setIcon(R.drawable.ic_baseline_error_24)
        .setIconAttribute(android.R.attr.alertDialogIcon)
        .create()
        .show()
}