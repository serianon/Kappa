package com.github.serianon.kappa

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

/**
 * A card displayed in a grid of many cards.
 */
class GridCardViewHolder(itemView: View, private val mOnClickListener: CardViewHolderListener) : AbstractCardViewHolder(itemView, mOnClickListener) {

    private val mCardView: CardView = itemView.findViewById(R.id.grid_card_view)

    private val mNumberTextView: TextView = itemView.findViewById(R.id.grid_card_value)

    override fun bind(cardValue: String) {
        mNumberTextView.textSize = 50.0f
        mNumberTextView.text = cardValue
        mCardView.setOnClickListener(this)
    }

    override fun onClick(view: View?) = mOnClickListener.onCardViewClicked(view as CardView, adapterPosition)

    companion object {
        const val LAYOUT_RES = R.layout.layout_grid_card
    }
}
