package com.bandonleon.cryptofolio.feature.portfolio.view

import android.support.v4.widget.SwipeRefreshLayout
import com.bandonleon.cryptofolio.R
import com.bandonleon.cryptofolio.feature.portfolio.PortfolioContract
import com.bandonleon.cryptofolio.feature.portfolio.PortfolioContract.PorfolioView.LoadListener
import com.bandonleon.cryptofolio.feature.portfolio.model.AssetRepository
import com.bandonleon.cryptofolio.feature.portfolio.model.CoinStatRepository
import com.bandonleon.cryptofolio.feature.portfolio.presenter.PortfolioPresenter
import com.bandonleon.cryptofolio.framework.recycler.RecyclerFragment
import com.bandonleon.mvp.Presenter

/**
 * Created by dombhuphaibool on 2/27/18.
 */
class PortfolioFragment : RecyclerFragment<PortfolioAdapter>(), PortfolioContract.PorfolioView {

    override fun getLayoutProvider(): LayoutProvider {
        return LayoutProvider(R.layout.fragment_porfolio, R.id.swipe_refresh, R.id.recycler_view)
    }

    override fun createAdapter(): PortfolioAdapter {
        return PortfolioAdapter()
    }

    override fun createPresenter(): Presenter {
        val coinStatRepository = CoinStatRepository(context!!)
        val assetRepository = AssetRepository(context!!)
        return PortfolioPresenter(coinStatRepository, assetRepository)
    }

    override fun setLoadListener(listener: LoadListener?) {
        setOnRefreshListener(
                if (listener != null) {
                    object: SwipeRefreshLayout.OnRefreshListener {
                        override fun onRefresh() {
                            listener.onLoad()
                        }
                    }
                } else {
                    null
                }
        )
    }

    override fun updateView(coinAssets: List<CoinAsset>) {
        adapter?.updateCoinAssets(coinAssets)
        /*
        for (coinAsset in coinAssets) {
            Log.e("COIN_STATS", "${coinAsset.coinName} is priced at ${coinAsset.price}")
        }
        */
    }

    companion object {
        fun newInstance(): PortfolioFragment = PortfolioFragment()
    }
}