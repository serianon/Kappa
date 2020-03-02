package com.github.serianon.ppp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Showing all cards.
 */
class CardsFragment : Fragment() {

    private val mVerticalGridLayout = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

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
}
