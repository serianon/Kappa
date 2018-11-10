package com.github.serianon.ppp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_grid.*

/**
 * Showing all cards in an overview for fast-access.
 */
class GridFragment : Fragment() {

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_grid, null)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = GridAdapter(resources.getStringArray(R.array.fibonacci), this)
        recyclerView.setHasFixedSize(true)

        prepareTransitions()
        postponeEnterTransition()

        return view
    }

    private fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context).inflateTransition(R.transition.grid_exit)

        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val selectedViewHolder = recyclerview.findViewHolderForAdapterPosition(0) as RecyclerView.ViewHolder
                names?.get(0)?.let { name ->
                    sharedElements?.put(name, selectedViewHolder.itemView.findViewById(R.id.cardview))
                }
            }
        })
    }

}
