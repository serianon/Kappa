package com.github.serianon.kappa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.serianon.kappa.CardsAdapter.ViewType.GRID
import com.github.serianon.kappa.CardsAdapter.ViewType.PAGE

class CardsAdapter(private val mCardValues: Array<String>, private val mCardsFragment: CardsFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        GRID, PAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        GRID.ordinal -> GridCardViewHolder(inflateView(parent, GridCardViewHolder.LAYOUT_RES), createSwitchLayoutListener())
        PAGE.ordinal -> PageCardViewHolder(inflateView(parent, PageCardViewHolder.LAYOUT_RES), createSwitchLayoutListener())
        else -> throw IllegalStateException("Unknown RecyclerView-Layout-State")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as AbstractCardViewHolder).bind(mCardValues[position])

    override fun getItemViewType(position: Int) = when {
        mCardsFragment.isGridLayout() -> GRID.ordinal
        mCardsFragment.isPageLayout() -> PAGE.ordinal
        else -> -1
    }

    override fun getItemCount() = mCardValues.size

    private fun inflateView(parent: ViewGroup, layoutRes: Int) = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)

    private fun createSwitchLayoutListener() = object : AbstractCardViewHolder.CardViewHolderListener {
        override fun onCardViewClicked(cardView: CardView, adapterPosition: Int): Unit = mCardsFragment.run {
            switchLayout()
            scrollTo(adapterPosition)
            notifyItemRangeChanged(0, itemCount)
        }
    }
}
