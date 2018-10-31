package com.github.serianon.ppp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.*
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.TextView

class CardActivity : AppCompatActivity() {

    private var mCardPagerAdapter: CardPagerAdapter? = null

    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.postponeEnterTransition(this)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        mCardPagerAdapter = CardPagerAdapter(supportFragmentManager)
        mViewPager = findViewById(R.id.viewpager)
        mViewPager!!.adapter = mCardPagerAdapter

        enableViewPagerPreviews()

        loadCurrentItemFromIntentExtra()
    }

    private fun enableViewPagerPreviews() {
        // Left and right card preview
        mViewPager!!.clipToPadding = false
        mViewPager!!.setPadding(100, 0, 100, 0)
    }

    private fun loadCurrentItemFromIntentExtra() {
        val intent = intent
        if (intent != null && intent.hasExtra(CURRENT_ITEM_INDEX_KEY)) {
            mViewPager!!.currentItem = intent.getIntExtra(CURRENT_ITEM_INDEX_KEY, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.to_overview -> {
                val intent = Intent(this, OverviewActivity::class.java)
                val currentCardFragment = mCardPagerAdapter!!
                        .instantiateItem(mViewPager!!, mViewPager!!.currentItem) as CardFragment
                val currentCardView = currentCardFragment.view!!.findViewById<CardView>(R.id.cardview)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, currentCardView, getString(R.string.overview_transition_id))
                startActivity(intent, options.toBundle())
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    inner class CardPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val cardValues = resources.getStringArray(R.array.fibonacci)
            return CardFragment.newInstance(cardValues[position])
        }

        override fun getCount(): Int {
            return resources.getIntArray(R.array.fibonacci).size
        }
    }

    class CardFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.layout_card, container, false)
            val textView = rootView.findViewById<TextView>(R.id.number)
            textView.text = arguments!!.getString(CARD_VALUE_KEY)
            return rootView
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            ActivityCompat.startPostponedEnterTransition(activity!!)
        }

        companion object {

            private val CARD_VALUE_KEY = "CARD_VALUE"

            fun newInstance(cardValue: String): CardFragment {
                val fragment = CardFragment()
                val args = Bundle()
                args.putString(CARD_VALUE_KEY, cardValue)
                fragment.arguments = args
                return fragment
            }
        }

    }

    companion object {

        fun createIntent(context: Context, cardIndex: Int): Intent {
            val intent = Intent(context, CardActivity::class.java)
            intent.putExtra(CURRENT_ITEM_INDEX_KEY, cardIndex)
            return intent
        }

        private val CURRENT_ITEM_INDEX_KEY = "CURRENT_ITEM_INDEX_KEY"
    }

}
