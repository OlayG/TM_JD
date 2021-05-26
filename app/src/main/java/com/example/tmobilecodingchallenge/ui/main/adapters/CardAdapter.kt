package com.example.tmobilecodingchallenge.ui.main.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmobilecodingchallenge.data.models.Card
import com.example.tmobilecodingchallenge.databinding.CardItemBinding

class CardAdapter(
    private val cards: List<Card>
) : RecyclerView.Adapter<CardAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardAdapter.MyViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardAdapter.MyViewHolder, position: Int) =
        with(holder) {
            loadData(cards[position])
        }

    class MyViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun loadData(card: Card) = with(binding) {
            when (card.card_type) {
                "text" -> {
                    with(binding) {
                        textTv.text = card.card.value
                        textTv.textSize = card.card.attributes.font.size.toFloat()
                        textTv.setTextColor(Color.parseColor(card.card.attributes.text_color))
                        textCard.isVisible = true
                    }
                }

                "title_description" -> {
                    with(binding) {
                        titleTv.text = card.card.title.value
                        titleTv.textSize = card.card.title.attributes.font.size.toFloat()
                        titleTv.setTextColor(Color.parseColor(card.card.title.attributes.text_color))

                        descriptionTv.text = card.card.description.value
                        descriptionTv.textSize =
                            card.card.description.attributes.font.size.toFloat()
                        descriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.text_color))
                        titleDescriptionCard.isVisible = true
                    }
                }

                "image_title_description" -> {
                    with(binding) {
                        imageTitleTv.text = card.card.title.value
                        imageTitleTv.textSize =
                            card.card.title.attributes.font.size.toFloat()
                        imageTitleTv.setTextColor(Color.parseColor(card.card.title.attributes.text_color))

                        imageDescriptionTv.text = card.card.description.value
                        imageDescriptionTv.textSize =
                            card.card.description.attributes.font.size.toFloat()
                        imageDescriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.text_color))

                        cardImage.layoutParams.height = card.card.image.size.height
                        cardImage.layoutParams.width = card.card.image.size.width
                        cardImage.requestLayout()
                        Glide.with(cardImage)
                            .load(card.card.image.url)
                            .into(cardImage)

                        imageTitleDescriptionCard.visibility = ConstraintLayout.VISIBLE
                    }
                }
            }
        }
    }

}