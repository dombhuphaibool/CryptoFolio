package com.bandonleon.cryptofolio.feature.porfolio.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import java.util.*

/**
 * Created by dombhuphaibool on 3/1/18.
 */
@Dao
interface TransactionDao {
/*
    @Query("SELECT * FROM Transaction")
    fun getAll(): List<Transaction>

    // Once this issue is fixed: https://youtrack.jetbrains.com/issue/KT-17959
    // change :arg0 to :coinName
    @Query("SELECT * FROM Transaction WHERE date = :arg0")
    fun getTransaction(date: String): Transaction?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: Transaction)

    @Query("DELETE FROM Transaction")
    fun deleteAll()
*/
}