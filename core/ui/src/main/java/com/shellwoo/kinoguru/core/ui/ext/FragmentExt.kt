package com.shellwoo.kinoguru.core.ui.ext

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.shellwoo.kinoguru.core.ui.FragmentResultContract
import com.shellwoo.kinoguru.core.ui.R
import java.io.Serializable

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