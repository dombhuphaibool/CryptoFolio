package com.bandonleon.cryptofolio.feature.portfolio.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * Created by dombhuphaibool on 2/25/18.
 */
@Dao
interface CoinStatDao {

    @Query("SELECT * FROM CoinStat")
    fun getAll(): List<CoinStat>

    @Query("SELECT * FROM CoinStat WHERE id = :coinName")
    fun getCoin(coinName: String): CoinStat?

    @Insert(onConflict = REPLACE)
    fun insert(coinStat: CoinStat)

    @Query("DELETE FROM CoinStat")
    fun deleteAll()
}