package com.github.serianon.ppp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView

/**
 * Showing all cards in an overview for fast-access.
 */
class CardGridViewFragment : Fragment() {

    private var mCardsAdapter: CardsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cardValues = resources.getStringArray(R.array.fibonacci)
        mCardsAdapter = CardsAdapter(activity!!, cardValues)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cardgridview, null)

        val gridView = view.findViewById(R.id.gridview) as GridView
        gridView.numColumns = 3
        gridView.adapter = mCardsAdapter

        return view
    }

    inner class CardsAdapter(context: Context, private val mCardValues: Array<String>) : BaseAdapter() {

        private val mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int = mCardValues.size

        override fun getItem(position: Int): Any? = null

        override fun getItemId(position: Int): Long = -1

        @SuppressLint("InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View = convertView ?: mLayoutInflater.inflate(R.layout.layout_card, null)

            val textView = view.findViewById<TextView>(R.id.number)
            textView.text = mCardValues[position]

            val cardView = view.findViewById<CardView>(R.id.cardview)
            cardView.setOnClickListener {
                // TODO: Go to CardViewPagerFragment
            }

            return view
        }
    }

}
