package com.example.tmobilecodingchallenge.models

import com.google.gson.annotations.SerializedName

data class Card(
    val card: CardX,
    val card_type: CardType
)

enum class CardType {
    @SerializedName("text") TEXT,
    @SerializedName("title_description") TITLE_DESCRIPTION,
    @SerializedName("image_title_description") IMAGE_TITLE_DESCRIPTION,
}