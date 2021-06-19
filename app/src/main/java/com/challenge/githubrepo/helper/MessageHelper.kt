package com.challenge.githubrepo.helper

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, @StringRes message: Int) {
    val snack = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
    snack.setAction(android.R.string.ok) {
        snack.dismiss()
    }
    snack.show()
}
