package com.banancheg.model.data

import com.google.gson.annotations.SerializedName

class MeaningsDto(
    @SerializedName(FIELD_TRANSLATION) val translationDto: TranslationDto?,
    @SerializedName(FIELD_IMAGE_URL) val imageUrl: String?
) {
    companion object {
        private const val FIELD_TRANSLATION = "translation"
        private const val FIELD_IMAGE_URL = "imageUrl"
    }
}