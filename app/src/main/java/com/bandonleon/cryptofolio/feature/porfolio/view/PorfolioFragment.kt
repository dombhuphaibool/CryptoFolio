package com.bandonleon.cryptofolio.feature.porfolio.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bandonleon.cryptofolio.R
import com.bandonleon.cryptofolio.feature.porfolio.PorfolioContract
import com.bandonleon.cryptofolio.feature.porfolio.PorfolioContract.PorfolioView.LoadListener
import com.bandonleon.cryptofolio.feature.porfolio.model.AssetRepository
import com.bandonleon.cryptofolio.feature.porfolio.model.CoinStatRepository
import com.bandonleon.cryptofolio.feature.porfolio.presenter.PorfolioPresenter
import com.bandonleon.cryptofolio.framework.mvp.MvpFragment
import com.bandonleon.mvp.LoadState
import com.bandonleon.mvp.Presenter

/**
 * Created by dombhuphaibool on 2/27/18.
 */
class PorfolioFragment : MvpFragment(), PorfolioContract.PorfolioView {

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: PorfolioAdapter? = null

    private var _loadState: LoadState = LoadState.LOADED
    override var loadState: LoadState
        get() = _loadState
        set(value) {
            _loadState = value
            swipeRefreshLayout?.isRefreshing = (_loadState == LoadState.LOADING)
        }

    private var loadListener: LoadListener? = null

    override fun createPresenter(): Presenter {
        val coinStatRepository = CoinStatRepository(context!!)
        val assetRepository = AssetRepository(context!!)
        return PorfolioPresenter(coinStatRepository, assetRepository)
    }

    override fun setLoadListener(listener: LoadListener) {
        loadListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_porfolio, container, false)
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh)
        swipeRefreshLayout?.setOnRefreshListener {
            loadListener?.onLoad()
        }
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(context!!)
        adapter = PorfolioAdapter()
        recyclerView?.adapter = adapter
        return rootView
    }

    override fun onDestroyView() {
        loadListener = null
        super.onDestroyView()
    }

    override fun updateView(coinAssets: List<CoinAsset>) {
        adapter?.updateCoinAssets(coinAssets)
        /*
        for (coinAsset in coinAssets) {
            Log.e("COIN_STATS", "${coinAsset.coinName} is priced at ${coinAsset.price}")
        }
        */
    }
}