package com.bandonleon.cryptofolio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.bandonleon.cryptofolio.feature.portfolio.model.Asset
import com.bandonleon.cryptofolio.feature.portfolio.view.PortfolioFragment
import com.bandonleon.cryptofolio.persistence.CoinDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener listener@ { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    displayFragment(PortfolioFragment.newInstance())
                    return@listener true
                }

                R.id.navigation_dashboard -> {
                    displayFragment(PortfolioFragment.newInstance())
                    return@listener true
                }

                R.id.navigation_notifications -> {
                    displayFragment(PortfolioFragment.newInstance())
                    return@listener true
                }

                else -> return@listener false
            }
        }

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

    private fun displayFragment(fragment: Fragment) {

    }
}
