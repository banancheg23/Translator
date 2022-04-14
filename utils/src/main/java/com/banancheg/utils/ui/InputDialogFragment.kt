package com.banancheg.utils.ui

import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class InputDialogFragment(context: Context) : AlertDialog.Builder(context) {

    companion object {

        fun newInstance(context: Context, title: String?, onAccessButtonClick: (String) -> Unit): AlertDialog.Builder {
            val editText = EditText(context)
            return AlertDialog.Builder(context)
                .setTitle(title)
                .setView(editText)
                .setPositiveButton("Find") { dialog, which ->
                    val text: String = editText.text.toString()
                    onAccessButtonClick(text)
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.cancel()
                }
        }
    }
}