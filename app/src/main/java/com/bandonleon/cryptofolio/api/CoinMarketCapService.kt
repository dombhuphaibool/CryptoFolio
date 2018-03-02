package com.bandonleon.cryptofolio.api

import com.bandonleon.cryptofolio.feature.porfolio.model.CoinStat
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by dombhuphaibool on 2/24/18.
 */
interface CoinMarketCapService {

    @GET("ticker/{coin}/")
    fun getCoinStat(@Path("coin") coin: String): Observable<List<CoinStat>>


    companion object {
        const val BASE_URL: String = "https://api.coinmarketcap.com/v1/"

        fun create(): CoinMarketCapService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(CoinMarketCapService::class.java)
        }
    }
}