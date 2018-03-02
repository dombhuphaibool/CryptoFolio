package com.bandonleon.cryptofolio.feature.porfolio

import com.bandonleon.cryptofolio.feature.porfolio.view.CoinAsset
import com.bandonleon.mvp.View

/**
 * Created by dombhuphaibool on 2/27/18.
 */
interface PorfolioContract {

    interface PorfolioView : View {

        fun updateView(coinAssets: List<CoinAsset>)

        fun setLoadListener(listener: LoadListener)

        interface LoadListener {
            fun onLoad()
        }
    }
}