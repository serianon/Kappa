package com.github.serianon.ppp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.cardview.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val CARD_VALUE_KEY = "CARD_VALUE"

@Deprecated("Nobody likes VierPager")
class CardPagerAdapter(fragmentManager: FragmentManager, private val cardValues: Array<String>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = CardFragment.newInstance(cardValues[position])

    override fun getCount(): Int = cardValues.size

    class CardFragment : Fragment() {

        companion object {
            fun newInstance(cardValue: String): CardFragment {
                return CardFragment().apply {
                    arguments = Bundle().apply {
                        putString(CARD_VALUE_KEY, cardValue)
                    }
                }
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val cardLayout = inflater.inflate(R.layout.layout_card, container, false)
            val cardValue = arguments?.getString(CARD_VALUE_KEY)

            val cardView = cardLayout.findViewById<CardView>(R.id.card_view)
            cardView.transitionName = cardValue

            val numberTextView = cardLayout.findViewById<TextView>(R.id.card_value)
            numberTextView.textSize = 160.0f
            numberTextView.text = cardValue

            return cardLayout
        }

    }

}