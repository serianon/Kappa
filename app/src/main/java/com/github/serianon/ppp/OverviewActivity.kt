package com.github.serianon.ppp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView

class OverviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)
        setSupportActionBar(toolbar)

        val gridView = findViewById<GridView>(R.id.gridview)
        gridView.numColumns = 3

        val cardValues = resources.getStringArray(R.array.fibonacci)
        val cardsAdapter = CardsAdapter(this, cardValues)
        gridView.adapter = cardsAdapter
    }

    inner class CardsAdapter(context: Context, private val mCardValues: Array<String>) : BaseAdapter() {

        private val mLayoutInflater: LayoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return mCardValues.size
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return -1
        }

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
