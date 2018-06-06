package com.bandonleon.cryptofolio.feature.portfolio.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by dombhuphaibool on 2/25/18.
 */
@Entity(tableName="CoinStat")
data class CoinStat(
        @PrimaryKey var id: String,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "symbol") var symbol: String,
        @ColumnInfo(name = "rank") var rank: Int,
        @ColumnInfo(name = "price_usd") var price_usd: Float,
        @ColumnInfo(name = "price_btc") var price_btc: Float,
        @ColumnInfo(name = "day_volume_usd") var day_volume_usd: Float,
        @ColumnInfo(name = "market_cap_usd") var market_cap_usd: Float,
        @ColumnInfo(name = "available_supply") var available_supply: Float,
        @ColumnInfo(name = "total_supply") var total_supply: Float,
        @ColumnInfo(name = "max_supply") var max_supply: Float,
        @ColumnInfo(name = "percent_change_1h") var percent_change_1h: Float,
        @ColumnInfo(name = "percent_change_24h") var percent_change_24h: Float,
        @ColumnInfo(name = "percent_change_7d") var percent_change_7d: Float,
        @ColumnInfo(name = "last_updated") var last_updated: Int) {

    constructor() : this("0", "", "", 0, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1)
}