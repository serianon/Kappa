package com.github.serianon.ppp

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

    private val mVerticalGridLayout = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)

    private val mHorizontalLinearLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_cards, null)

        view.findViewById<RecyclerView>(R.id.recyclerview).run {
            layoutManager = mVerticalGridLayout
            adapter = CardsAdapter(resources.getStringArray(R.array.fibonacci), this@CardsFragment)
            setHasFixedSize(true)
        }

        return view
    }

    fun switchLayout() = if (isVerticalGridLayout()) useHorizontalLinearLayout() else useVerticalGridLayout()

    fun scrollTo(position: Int) = findRecyclerView()?.layoutManager?.scrollToPosition(position)

    private fun isVerticalGridLayout() = findRecyclerView().let { if (it == null) false else it.layoutManager is GridLayoutManager }

    private fun isHorizontalLinearLayout() = findRecyclerView().let { if (it == null) false else it.layoutManager is LinearLayoutManager }

    private fun useHorizontalLinearLayout() = findRecyclerView()?.run {
        layoutManager = mHorizontalLinearLayout
        LinearSnapHelper().attachToRecyclerView(this)
    }

    private fun useVerticalGridLayout() = findRecyclerView()?.run {
        layoutManager = mVerticalGridLayout
        onFlingListener = null
    }

    private fun findRecyclerView() = view?.findViewById<RecyclerView>(R.id.recyclerview)
}
