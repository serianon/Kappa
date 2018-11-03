package com.github.serianon.ppp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class GridAdapter(private val mCardValues: Array<String>) : RecyclerView.Adapter<GridAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_card, parent, false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(mCardValues[position])
    }

    override fun getItemCount(): Int = mCardValues.size

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val numberTextView : TextView = itemView.findViewById(R.id.card_value)

        fun bind(cardValue: String) {
            numberTextView.text = cardValue
        }

    }

}
