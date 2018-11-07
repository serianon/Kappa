package com.github.serianon.ppp

import android.support.transition.TransitionSet
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class GridAdapter(private val mCardValues: Array<String>, private val mFragment: Fragment) : RecyclerView.Adapter<GridAdapter.CardViewHolder>() {

    interface CardViewHolderListener {
        fun onCardViewClicked(cardView: CardView, adapterPosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutCardView = LayoutInflater.from(parent.context).inflate(R.layout.layout_card, parent, false)
        return CardViewHolder(
                layoutCardView,
                object : CardViewHolderListener {
                    override fun onCardViewClicked(cardView: CardView, adapterPosition: Int) {
                        (mFragment.exitTransition as TransitionSet?)?.excludeTarget(cardView, true)
                        //val transitioningView: View = cardView.findViewById(R.id.cardview)
                        mFragment.fragmentManager
                                ?.beginTransaction()
                                //?.setReorderingAllowed(true)
                                //?.addSharedElement(transitioningView, transitioningView.transitionName)
                                ?.replace(R.id.activity_content, CardPagerFragment.newInstance(adapterPosition))
                                ?.addToBackStack(null)
                                ?.commit()
                    }
                }
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(mCardValues[position])
    }

    override fun getItemCount(): Int = mCardValues.size

    inner class CardViewHolder(itemView: View, private val mViewHolderListener: CardViewHolderListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val mCardView: CardView = itemView.findViewById(R.id.cardview)
        private val mNumberTextView: TextView = itemView.findViewById(R.id.card_value)

        fun bind(cardValue: String) {
            mNumberTextView.text = cardValue
            mCardView.transitionName = cardValue
            mCardView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            mViewHolderListener.onCardViewClicked(view as CardView, adapterPosition)
        }

    }

}
