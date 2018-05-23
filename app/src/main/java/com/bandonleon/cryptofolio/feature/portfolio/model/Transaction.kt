package com.bandonleon.cryptofolio.feature.portfolio.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by dombhuphaibool on 3/1/18.
 */
@Entity(tableName="Transaction")
class Transaction(
        @PrimaryKey var date: String,
        @ColumnInfo(name = "coin_id") var coinId: String,
        @ColumnInfo(name = "exchange_id") var exchangeId: Int,
        @ColumnInfo(name = "amount") var amount: Float) {

    constructor() : this("" /* Date(System.currentTimeMillis()) */, "", 0, 0f)
}