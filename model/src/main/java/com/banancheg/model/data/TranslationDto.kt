package com.banancheg.model.data

import com.google.gson.annotations.SerializedName

class TranslationDto(@SerializedName(FIELD_TRANSLATION) val translation: String?) {
    companion object {
        private const val FIELD_TRANSLATION = "text"
    }
}