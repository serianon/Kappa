package com.github.serianon.ppp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val CURRENT_ITEM_INDEX_KEY = "CURRENT_ITEM_INDEX_KEY"
private const val CARD_VALUE_KEY = "CARD_VALUE"

/**
 * Showing a single card at almost fullscreen with preview of the next cards left and right.
 */
class CardViewPagerFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(currentItemIndex: Int): CardViewPagerFragment {
            return CardViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(CURRENT_ITEM_INDEX_KEY, currentItemIndex)
                }
            }
        }
    }

    private var currentItemIndex: Int? = null
    private var mCardPagerAdapter: CardPagerAdapter? = null
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            currentItemIndex = it.getInt(CURRENT_ITEM_INDEX_KEY)
        }

        mCardPagerAdapter = CardPagerAdapter(childFragmentManager)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cardviewpager, null)

        mViewPager = view.findViewById(R.id.viewpager) as ViewPager
        mViewPager!!.adapter = mCardPagerAdapter

        // Left and right card preview
        mViewPager!!.clipToPadding = false
        mViewPager!!.setPadding(100, 0, 100, 0)

        return view
    }

    inner class CardPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val cardValues = resources.getStringArray(R.array.fibonacci)

        override fun getItem(position: Int): Fragment = CardPageFragment.newInstance(cardValues[position])

        override fun getCount(): Int = cardValues.size

    }

    class CardPageFragment : Fragment() {

        companion object {
            fun newInstance(cardValue: String): CardPageFragment {
                return CardPageFragment().apply {
                    arguments = Bundle().apply {
                        putString(CARD_VALUE_KEY, cardValue)
                    }
                }
            }
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.layout_card, container, false)

            val textView = rootView.findViewById<TextView>(R.id.number)
            textView.text = arguments!!.getString(CARD_VALUE_KEY)

            return rootView
        }

    }

}
