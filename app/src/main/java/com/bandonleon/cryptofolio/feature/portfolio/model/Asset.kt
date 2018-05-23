package com.bandonleon.cryptofolio.feature.portfolio.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by dombhuphaibool on 3/1/18.
 */
@Entity(tableName="Asset")
data class Asset(
        @PrimaryKey var id: String,
        @ColumnInfo(name = "amount") var amount: Float) {

    constructor() : this("", 0f)
}