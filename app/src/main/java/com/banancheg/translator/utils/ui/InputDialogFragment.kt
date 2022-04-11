package com.banancheg.translator.utils.ui

import android.content.Context
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class InputDialogFragment(context: Context) : AlertDialog.Builder(context) {

    companion object {

        private const val TITLE_EXTRA = "89cbce59-e28f-418f-b470-ff67125c2e2f"
        private const val MESSAGE_EXTRA = "0dd00b66-91c2-447d-b627-530065040905"

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