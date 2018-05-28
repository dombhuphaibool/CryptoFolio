package com.bandonleon.cryptofolio.framework.recycler

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bandonleon.cryptofolio.R
import com.bandonleon.cryptofolio.framework.mvp.MvpFragment
import com.bandonleon.mvp.LoadState

/**
 * A base RecyclerFragment which supports swipe to refresh and load feedback
 */
abstract class RecyclerFragment<A : RecyclerView.Adapter<*>> : MvpFragment(){

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private var onRefreshListener: OnRefreshListener? = null

    private var _loadState: LoadState = LoadState.LOADED
    override var loadState: LoadState
        get() = _loadState
        set(value) {
            _loadState = value
            swipeRefreshLayout?.isRefreshing = (_loadState == LoadState.LOADING)
        }

    protected val adapter: A by lazy {
        createAdapter()
    }

    /**
     * Subclasses must override this method to provide an implementation of a [RecyclerView.Adapter]
     */
    abstract fun createAdapter(): A

    /**
     * Subclasses must override this method to provide a [LayoutProvider]
     */
    abstract fun getLayoutProvider(): LayoutProvider

    /**
     * Subclasses should not override this method
     */
    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutProvider = getLayoutProvider()
        val rootView = inflater.inflate(layoutProvider.layoutId, container, false)
        swipeRefreshLayout = rootView.findViewById(layoutProvider.swipeRefreshId)
        recyclerView = rootView.findViewById(layoutProvider.recyclerId)
        val recyclerLayoutManager = LinearLayoutManager(context!!)
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(context, recyclerLayoutManager.orientation).apply {
            setDrawable(ContextCompat.getDrawable(context!!, R.drawable.item_divider)!!)
        }
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.recycler_item_vspacing)))

        return rootView
    }

    /**
     * This method sets an [OnRefreshListener] to respond to the swipe to refresh gesture
     */
    fun setOnRefreshListener(listener: OnRefreshListener?) {
        swipeRefreshLayout.setOnRefreshListener {
            listener?.onRefresh()
        }
    }

    /**
     * A convenience class used to provide xml resource IDs
     */
    class LayoutProvider(@LayoutRes val layoutId: Int, @IdRes val swipeRefreshId: Int, @IdRes val recyclerId: Int)
}