package com.banancheg.translator.model.data

import com.google.gson.annotations.SerializedName

class Meanings(
    @SerializedName(FIELD_TRANSLATION) val translation: Translation?,
    @SerializedName(FIELD_IMAGE_URL) val imageUrl: String?
) {
    companion object {
        private const val FIELD_TRANSLATION = "translation"
        private const val FIELD_IMAGE_URL = "imageUrl"
    }
}