package com.bandonleon.cryptofolio.feature.portfolio.model

import android.content.Context
import android.net.ConnectivityManager
import com.bandonleon.cryptofolio.api.CoinMarketCapService
import com.bandonleon.cryptofolio.persistence.CoinDatabase
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by dombhuphaibool on 2/25/18.
 */
class CoinStatRepository(val context: Context) {

    val coinDatabase by lazy {
        CoinDatabase.getInstance(context)
    }

    val coinMarketCapService by lazy {
        CoinMarketCapService.create()
    }

    fun getCoinStat(coinName: String): Observable<CoinStat> {
        return if (isNetworkAvailable()) getCoinStatFromNetwork(coinName) else getCoinStatFromDatabase(coinName)
    }

    private fun getCoinStatFromNetwork(coinName: String): Observable<CoinStat> {
        return coinMarketCapService.getCoinStat(coinName)
                .subscribeOn(Schedulers.io())
                .flatMapIterable { coinStats -> coinStats }
                .doOnNext({ coinStat -> coinDatabase.coinStatDao().insert(coinStat) })
    }

    private fun getCoinStatFromDatabase(coinName: String): Observable<CoinStat> {
        val observable: Observable<CoinStat> = Observable.create {
                observer ->
                    val coinStat = coinDatabase.coinStatDao().getCoin(coinName)
                    if (coinStat != null) {
                        observer.onNext(coinStat)
                    }
                    observer.onComplete()
        }
        return observable.subscribeOn(Schedulers.io())
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.getActiveNetworkInfo()
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting()
    }
}