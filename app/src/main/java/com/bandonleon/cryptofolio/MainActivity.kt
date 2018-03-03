package com.bandonleon.cryptofolio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug.waitForDebugger
import android.support.design.widget.BottomNavigationView
import com.bandonleon.cryptofolio.feature.porfolio.model.Asset
import com.bandonleon.cryptofolio.persistence.CoinDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // waitForDebugger()

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
