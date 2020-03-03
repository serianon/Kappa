package com.github.serianon.kappa

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Showing all cards.
 */
class CardsFragment : Fragment() {

    private val mGridLayout = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

    private val mPageLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_cards, null)

        view.findViewById<RecyclerView>(R.id.recyclerview).run {
            layoutManager = mGridLayout
            adapter = CardsAdapter(resources.getStringArray(R.array.fibonacci), this@CardsFragment)
            setHasFixedSize(true)
        }

        return view
    }

    fun switchLayout() = if (isGridLayout()) usePageLayout() else useGridLayout()

    fun scrollTo(position: Int) = findRecyclerView()?.layoutManager?.scrollToPosition(position)

    fun isGridLayout() = findRecyclerView().let { if (it == null) false else it.layoutManager is GridLayoutManager }

    fun isPageLayout() = findRecyclerView().let { if (it == null) false else it.layoutManager is LinearLayoutManager }

    private fun useGridLayout() = findRecyclerView()?.run {
        layoutManager = mGridLayout
        onFlingListener = null
        adapter?.notifyDataSetChanged()
    }

    private fun usePageLayout() = findRecyclerView()?.run {
        layoutManager = mPageLayout
        LinearSnapHelper().attachToRecyclerView(this)
        adapter?.notifyDataSetChanged()
    }

    private fun findRecyclerView() = view?.findViewById<RecyclerView>(R.id.recyclerview)
}
