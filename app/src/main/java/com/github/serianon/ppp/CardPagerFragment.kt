package com.github.serianon.ppp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.ViewPager
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val CURRENT_ITEM_INDEX_KEY = "CURRENT_ITEM_INDEX_KEY"

/**
 * Showing a single card at almost fullscreen with previews of the next cards left and right.
 */
class CardPagerFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(currentItemIndex: Int): CardPagerFragment {
            return CardPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(CURRENT_ITEM_INDEX_KEY, currentItemIndex)
                }
            }
        }
    }

    private lateinit var mCardPagerAdapter: CardPagerAdapter
    private var mCurrentItemIndex: Int? = null
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mCurrentItemIndex = it.getInt(CURRENT_ITEM_INDEX_KEY)
        }
        mCardPagerAdapter = CardPagerAdapter(childFragmentManager, resources.getStringArray(R.array.fibonacci))
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_viewpager, null)

        mViewPager = view.findViewById(R.id.viewpager)
        mViewPager?.adapter = mCardPagerAdapter
        mViewPager?.currentItem = mCurrentItemIndex ?: 0
        mViewPager?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                mCurrentItemIndex = position
            }
        })
        // Left and right card preview
        mViewPager?.clipToPadding = false
        mViewPager?.setPadding(100, 0, 100, 0)

        prepareSharedElementTransition()
//        if (savedInstanceState == null) {
//            postponeEnterTransition()
//        }

        return view
    }

    private fun prepareSharedElementTransition() {
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                names?.get(0)?.let { name ->
                    findCardView()?.let { cardView ->
                        sharedElements?.put(name, cardView)
                    }
                }
            }
        })
    }

    private fun findCardView(): CardView? {
        val currentFragment = mCardPagerAdapter.instantiateItem(mViewPager?.rootView!!, mCurrentItemIndex!!) as Fragment?
        return currentFragment?.view?.findViewById(R.id.cardview)
    }

}
