package com.bandonleon.cryptofolio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bandonleon.cryptofolio.feature.portfolio.model.Asset
import com.bandonleon.cryptofolio.feature.portfolio.view.PortfolioFragment
import com.bandonleon.cryptofolio.persistence.CoinDatabase
import kotlinx.android.synthetic.main.fragment_view_pager.*
import kotlin.concurrent.thread

/**
 * TODO: Add description here...
 */
class ViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            val coinDatabase = CoinDatabase.getInstance(activity!!.applicationContext)
            val assetDao = coinDatabase.assetDao()
            val assets = assetDao.getAll()
            if (assets == null || assets.isEmpty()) {
                assetDao.insert(Asset("bitcoin", 3.5f))
                assetDao.insert(Asset("ethereum", 22.5f))
            }
        }

        bottomNavigation.selectedItemId = R.id.navigation_home
    }

    private fun displayFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        // Calling addToBackStack() below will allow the use of back button
        // transaction.addToBackStack(null)
        transaction.commit()
    }
}