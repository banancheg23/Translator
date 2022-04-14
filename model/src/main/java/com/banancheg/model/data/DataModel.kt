package com.banancheg.model.data

import com.google.gson.annotations.SerializedName

class DataModel(
    @SerializedName(FIELD_TEXT) val text: String?,
    @SerializedName(FIELD_MEANINGS) val meanings: List<Meanings>?
) {
    companion object {
        private const val FIELD_TEXT = "text"
        private const val FIELD_MEANINGS = "meanings"
    }
}