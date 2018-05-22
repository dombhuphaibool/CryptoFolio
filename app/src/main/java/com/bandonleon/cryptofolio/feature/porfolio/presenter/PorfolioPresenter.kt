package com.bandonleon.cryptofolio.feature.porfolio.presenter

import android.util.Log
import com.bandonleon.cryptofolio.feature.porfolio.PorfolioContract.PorfolioView
import com.bandonleon.cryptofolio.feature.porfolio.PorfolioContract.PorfolioView.LoadListener
import com.bandonleon.cryptofolio.feature.porfolio.model.Asset
import com.bandonleon.cryptofolio.feature.porfolio.model.AssetRepository
import com.bandonleon.cryptofolio.feature.porfolio.model.CoinStat
import com.bandonleon.cryptofolio.feature.porfolio.model.CoinStatRepository
import com.bandonleon.cryptofolio.feature.porfolio.view.CoinAsset
import com.bandonleon.mvp.BasePresenter
import com.bandonleon.mvp.LoadState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by dombhuphaibool on 2/27/18.
 */
class PorfolioPresenter(private val coinStatRepository: CoinStatRepository,
                        private val assetRepository: AssetRepository) : BasePresenter<PorfolioView>(), LoadListener {

    val assets: MutableMap<String, Asset> = HashMap<String, Asset>()
    lateinit var coins: MutableList<CoinStat>
    lateinit var coinAssets: List<CoinAsset>

    override fun onBind(view: PorfolioView) {
        super.onBind(view)
        view.setLoadListener(this)
        coins = ArrayList<CoinStat>()
        coinAssets = ArrayList<CoinAsset>()
        getAssets()
    }

    override fun onLoad() {
        if (!assets.isEmpty()) {
            val coinList = assets.map { (id, asset) -> asset.id }
            getCoinStats(coinList)
        } else {
            view.loadState = LoadState.LOADED
        }
    }

    private fun getAssets() {
        // coinList = listOf("bitcoin", "ethereum")
        assetRepository.getAssets()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.loadState = LoadState.LOADING
                }
                .doOnSuccess {
                    view.loadState = LoadState.LOADED
                }
                .doOnError {
                    view.loadState = LoadState.LOADED
                }
                .subscribe(
                        { loadedAssets ->
                            assets.clear()
                            assets.putAll(loadedAssets.associate { asset -> (asset.id to asset) })
                            val coinList = assets.map { (id, asset) -> asset.id }
                            getCoinStats(coinList)
                        },
                        { error -> Log.e("COIN_STATS", error.message) }
                )
    }

    private fun getAssetAmount(coinName: String): Float {
        return assets.get(coinName)?.amount ?: 0f
    }

    private fun onCoinsLoaded() {
        coinAssets = coins.map {
            coin -> CoinAsset(
                coin.name,
                getAssetAmount(coin.id),
                coin.symbol,
                coin.price_usd,
                coin.percent_change_1h,
                coin.percent_change_24h,
                coin.percent_change_7d)
        }
        view.updateView(coinAssets)
    }

    private fun getCoinStats(coinList: List<String>) {
        clearDisposables()
        coins.clear()
        addDisposable(Observable.fromIterable(coinList)
                .flatMap { coinName -> coinStatRepository.getCoinStat(coinName) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.loadState = LoadState.LOADING
                }
                .doOnComplete {
                    view.loadState = LoadState.LOADED
                    onCoinsLoaded()
                }
                .doOnError {
                    view.loadState = LoadState.LOADED
                }
                .subscribe(
                        {
                            coinStat ->
                            Log.e("COIN_STATS", coinStat.toString())
                            coins.add(coinStat)
                        },
                        {
                            error ->
                            Log.e("COIN_STATS", error.message)
                        }
        ))
    }

    private fun getCoinStat(coinName: String) {
        addDisposable(coinStatRepository.getCoinStat(coinName)
                .subscribe(
                        {
                            coinStat ->
                            Log.e("COIN_STATS", coinStat.toString())
                            coins.add(coinStat)
                            // priceTxt?.text = coinStat.price_usd.toString()
                        },
                        {
                            error ->
                            Log.e("COIN_STATS", error.message)
                        }
                ))

    }
}
