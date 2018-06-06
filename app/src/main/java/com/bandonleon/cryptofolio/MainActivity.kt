package com.bandonleon.cryptofolio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import androidx.navigation.findNavController
import com.bandonleon.cryptofolio.feature.portfolio.model.Asset
import com.bandonleon.cryptofolio.feature.portfolio.view.PortfolioFragment
import com.bandonleon.cryptofolio.persistence.CoinDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp()
    }
}
