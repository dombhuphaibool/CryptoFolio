package com.bandonleon.cryptofolio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug.waitForDebugger
import com.bandonleon.cryptofolio.feature.porfolio.model.Asset
import com.bandonleon.cryptofolio.persistence.CoinDatabase
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        waitForDebugger()

        thread(start = true) {
            val coinDatabase = CoinDatabase.getInstance(this)
            val assetDao = coinDatabase.assetDao()
            val assets = assetDao.getAll()
            if (assets == null || assets.isEmpty()) {
                assetDao.insert(Asset("bitcoin", 3.5f))
                assetDao.insert(Asset("ethereum", 22.5f))
            }
        }
    }
}
