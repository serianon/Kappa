package com.github.serianon.kappa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardsAdapter(private val mCardValues: Array<String>, private val mCardsFragment: CardsFragment) : RecyclerView.Adapter<AbstractCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractCardViewHolder = when {
        mCardsFragment.isGridLayout() -> GridCardViewHolder(
            LayoutInflater.from(parent.context).inflate(GridCardViewHolder.LAYOUT_RES, parent, false),
            createSwitchLayoutListener()
        )
        mCardsFragment.isPageLayout() -> PageCardViewHolder(
            LayoutInflater.from(parent.context).inflate(PageCardViewHolder.LAYOUT_RES, parent, false),
            createSwitchLayoutListener()
        )
        else -> throw IllegalStateException("Unknown RecyclerView-Layout-State")
    }

    override fun onBindViewHolder(holder: AbstractCardViewHolder, position: Int) = holder.bind(mCardValues[position])

    override fun getItemCount(): Int = mCardValues.size

    private fun createSwitchLayoutListener() = object : AbstractCardViewHolder.CardViewHolderListener {
        override fun onCardViewClicked(cardView: CardView, adapterPosition: Int): Unit = mCardsFragment.run {
            switchLayout()
            scrollTo(adapterPosition)
        }
    }
}
