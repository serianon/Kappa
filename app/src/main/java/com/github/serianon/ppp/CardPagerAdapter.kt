package com.github.serianon.ppp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val CARD_VALUE_KEY = "CARD_VALUE"

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
            val cardLayoutView = inflater.inflate(R.layout.layout_card, container, false)
            cardLayoutView.findViewById<TextView>(R.id.card_value).text = arguments?.getString(CARD_VALUE_KEY)
            return cardLayoutView
        }

    }

}