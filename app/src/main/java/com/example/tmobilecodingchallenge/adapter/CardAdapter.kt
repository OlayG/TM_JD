package com.example.tmobilecodingchallenge.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmobilecodingchallenge.R
import com.example.tmobilecodingchallenge.databinding.CardItemBinding
import com.example.tmobilecodingchallenge.models.Card
import com.example.tmobilecodingchallenge.models.CardType

class CardAdapter(
    private val cards: List<Card>
) : RecyclerView.Adapter<CardAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.loadCard(cards[position])
    }

    class MyViewHolder(
        private val binding: CardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadCard(card: Card) {
            when (card.card_type) {
                CardType.TEXT -> {
                    val titleData = TextViewData(
                        card.card.value,
                        card.card.attributes.font.size.toFloat(),
                        Color.parseColor(card.card.attributes.text_color)
                    )
                    loadCardData(titleData)
                }
                CardType.TITLE_DESCRIPTION,
                CardType.IMAGE_TITLE_DESCRIPTION -> {
                    val titleData = TextViewData(
                        card.card.title.value,
                        card.card.title.attributes.font.size.toFloat(),
                        Color.parseColor(card.card.title.attributes.text_color)
                    )
                    val descriptionData = TextViewData(
                        card.card.description.value,
                        card.card.description.attributes.font.size.toFloat(),
                        Color.parseColor(card.card.description.attributes.text_color)
                    )

                    val image = card.card.image
                    val imageData = if (image == null) null
                    else ImageViewData(image.url, image.size.height, image.size.width)

                    loadCardData(titleData, descriptionData, imageData)
                }
            }
        }

        private fun loadCardData(
            titleData: TextViewData,
            descriptionData: TextViewData? = null,
            imageData: ImageViewData? = null
        ) = with(binding) {
            description.isVisible = descriptionData != null
            image.isVisible = imageData != null

            title.text = titleData.text
            title.textSize = titleData.size
            title.setTextColor(titleData.color)

            descriptionData?.run {
                description.text = text
                description.textSize = size
                description.setTextColor(color)
            }

            imageData?.run {
                image.layoutParams.height = height
                image.layoutParams.width = width
                Glide.with(image).load(url).into(image)

                ConstraintSet().apply {
                    clone(clRoot)
                    connect(R.id.title, ConstraintSet.START, R.id.image, ConstraintSet.START)
                    connect(R.id.title, ConstraintSet.END, R.id.image, ConstraintSet.END)
                    connect(R.id.description, ConstraintSet.START, R.id.image, ConstraintSet.START)
                    connect(R.id.description, ConstraintSet.END, R.id.image, ConstraintSet.END)
                    applyTo(clRoot)
                }
            }
        }

        data class TextViewData(
            val text: String,
            val size: Float,
            @ColorInt val color: Int
        )

        data class ImageViewData(
            val url: String,
            val height: Int,
            val width: Int
        )
    }
}
