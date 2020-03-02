package com.github.serianon.ppp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardViewHolder(itemView: View, private val mOnClickListener: CardViewHolderListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    interface CardViewHolderListener {
        fun onCardViewClicked(cardView: CardView, adapterPosition: Int)
    }

    private val mCardView: CardView = itemView.findViewById(R.id.card_view)

    private val mNumberTextView: TextView = itemView.findViewById(R.id.card_value)

    fun bind(cardValue: String) {
        mNumberTextView.textSize = 50.0f
        mNumberTextView.text = cardValue
        mCardView.transitionName = cardValue
        mCardView.setOnClickListener(this)
    }

    override fun onClick(view: View?) = mOnClickListener.onCardViewClicked(view as CardView, adapterPosition)
}
