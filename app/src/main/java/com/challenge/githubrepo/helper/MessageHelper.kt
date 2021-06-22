package com.challenge.githubrepo.helper

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(@StringRes message: Int) {
    val snack = Snackbar.make(requireView(), message, Snackbar.LENGTH_INDEFINITE)
    snack.setAction(android.R.string.ok) {
        snack.dismiss()
    }
    snack.show()
}
