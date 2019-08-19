package com.example.conct_music.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Context.displayMessage(title: String, message: String) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(this.resources.getString(com.example.conct_music.R.string.ok)) { dialog, _ -> dialog.dismiss() }
    val dialog: AlertDialog = builder.create()
    dialog.show()
}