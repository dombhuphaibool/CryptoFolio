package com.bandonleon.cryptofolio.feature.portfolio.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by dombhuphaibool on 3/1/18.
 */
@Dao
interface AssetDao {

    @Query("SELECT * FROM Asset")
    fun getAll(): List<Asset>?

    @Query("SELECT * FROM Asset WHERE id = :coinName")
    fun getAsset(coinName: String): Asset?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asset: Asset)

    @Query("DELETE FROM Asset")
    fun deleteAll()
}