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
        @ColumnInfo(name = "transaction_type") var transactionType: Int,    // Deposit, Withdrawal, Purchase
        @ColumnInfo(name = "exchange_from_id") var exchangeFromId: Int,
        @ColumnInfo(name = "exchange_to_id") var exchangeToId: Int,
        @ColumnInfo(name = "coin_id") var coinId: String,
        @ColumnInfo(name = "coin_quantity") var coinQuantity: Float,
        @ColumnInfo(name = "transaction_amount") var amount: Float,         // If purchased
        @ColumnInfo(name = "currency_id") var currencyId: String) {         // If purchased

    constructor() : this("" /* Date(System.currentTimeMillis()) */, 0, 0, 0, "", 0f, 0f, "")

    companion object {
        const val SAMPLE_TRANSACTION_JSON = """{ "date" : "2018-04-23T18:25:43.511Z", "exchange_from_id" : "0", "exchange_to_id" : "1", "coin_id" : "btc", "coin_quantity" : "0.8", "transaction_amount" : "500.00", "currency_id" : "usd" }"""
    }

    enum class Type {
        PURCHASE,
        SENT,
        RECEIVED
    }

    enum class Exchange {
        WALLET,
        COINBASE,
        BINANCE,
        BITFINEX,
        BANK_WELLS_FARGO
    }
}