package com.github.serianon.ppp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardsAdapter(private val mCardValues: Array<String>, private val mCardsFragment: CardsFragment) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_card, parent, false),
        object : CardViewHolder.CardViewHolderListener {
            override fun onCardViewClicked(cardView: CardView, adapterPosition: Int): Unit = mCardsFragment.run {
                switchLayout()
                scrollTo(adapterPosition)
            }
        }
    )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind(mCardValues[position])

    override fun getItemCount(): Int = mCardValues.size
}
