package com.bandonleon.cryptofolio.feature.porfolio.model

import android.content.Context
import com.bandonleon.cryptofolio.persistence.CoinDatabase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by dombhuphaibool on 3/1/18.
 */
class AssetRepository(val context: Context) {

    val coinDatabase by lazy {
        CoinDatabase.getInstance(context)
    }

    fun getAsset(coinName: String): Single<Asset> {
        return getAssetFromDatabase(coinName)
    }

    fun getAssets(): Single<List<Asset>> {
        return getAssetsFromDatabase()
    }

    private fun getAssetFromDatabase(coinName: String): Single<Asset> {
        val single: Single<Asset> = Single.create {
            observer ->
            val asset = coinDatabase.assetDao().getAsset(coinName)
            if (asset != null) {
                observer.onSuccess(asset)
            } else {
                observer.onError(IllegalStateException("Coin $coinName not found"))
            }
        }
        return single.subscribeOn(Schedulers.io())
    }

    private fun getAssetsFromDatabase(): Single<List<Asset>> {
        val observable: Single<List<Asset>> = Single.create {
            observer ->
            val assets = coinDatabase.assetDao().getAll()
            if (assets != null) {
                observer.onSuccess(assets)
            } else {
                observer.onError(IllegalStateException("No assets found"))
            }
        }
        return observable.subscribeOn(Schedulers.io())
    }
}