package com.bandonleon.cryptofolio.feature.portfolio

import com.bandonleon.cryptofolio.feature.portfolio.view.CoinAsset
import com.bandonleon.mvp.View

/**
 * Created by dombhuphaibool on 2/27/18.
 */
interface PortfolioContract {

    interface PorfolioView : View {

        fun updateView(coinAssets: List<CoinAsset>)

        fun setLoadListener(listener: LoadListener?)

        interface LoadListener {
            fun onLoad()
        }
    }
}