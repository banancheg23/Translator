package com.banancheg.model.data

import com.google.gson.annotations.SerializedName

class SearchResultDto(
    @SerializedName(FIELD_TEXT) val text: String?,
    @SerializedName(FIELD_MEANINGS) val meanings: List<MeaningsDto?>?
) {
    companion object {
        private const val FIELD_TEXT = "text"
        private const val FIELD_MEANINGS = "meanings"
    }
}