package com.bandonleon.cryptofolio.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.bandonleon.cryptofolio.feature.porfolio.model.*

/**
 * Created by dombhuphaibool on 2/25/18.
 */
@Database(entities = arrayOf(
        Asset::class,
        Transaction::class,
        CoinStat::class), version = 1)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao
    abstract fun transactionDao(): TransactionDao
    abstract fun coinStatDao(): CoinStatDao

    companion object {
        private var INSTANCE: CoinDatabase? = null

        fun getInstance(context: Context): CoinDatabase {
            if (INSTANCE == null) {
                synchronized(CoinDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CoinDatabase::class.java, "coin.db")
                            // .allowMainThreadQueries() // TODO: Remove this!!!
                            .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
