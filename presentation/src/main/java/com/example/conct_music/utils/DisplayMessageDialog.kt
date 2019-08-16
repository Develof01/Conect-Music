package com.example.conct_music.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.conct_music.R

class DisplayMessageDialog(val context: Context) {

    fun displayMessage(title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(context.resources.getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}