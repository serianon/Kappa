package com.github.serianon.ppp

import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractCardViewHolder(itemView: View, private val mOnClickListener: CardViewHolderListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    interface CardViewHolderListener {
        fun onCardViewClicked(cardView: CardView, adapterPosition: Int)
    }

    abstract fun bind(cardValue: String)

    override fun onClick(view: View?) = mOnClickListener.onCardViewClicked(view as CardView, adapterPosition)
}
