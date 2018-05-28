package com.bandonleon.cryptofolio.persistence

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.bandonleon.cryptofolio.feature.portfolio.model.*

/**
 * Created by dombhuphaibool on 2/25/18.
 */
@Database(entities = arrayOf(
        Asset::class,
        Transaction::class,
        CoinStat::class), version = 2)
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
                            .addMigrations(MIGRATION_1_2)
                            .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private val MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE `Transaction`")
                database.execSQL("CREATE TABLE `Transaction` (`date` TEXT NOT NULL, `transaction_type` INTEGER NOT NULL, `exchange_from_id` INTEGER NOT NULL, `exchange_to_id` INTEGER NOT NULL, `coin_id` TEXT NOT NULL, `coin_quantity` REAL NOT NULL, `transaction_amount` REAL NOT NULL, `currency_id` TEXT NOT NULL, PRIMARY KEY(`date`))")
            }
        }
    }
}
