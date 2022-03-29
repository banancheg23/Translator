package com.banancheg.translator.model.data

import com.google.gson.annotations.SerializedName

class Translation(@SerializedName(FIELD_TRANSLATION) val translation: String?) {
    companion object {
        private const val FIELD_TRANSLATION = "text"
    }
}