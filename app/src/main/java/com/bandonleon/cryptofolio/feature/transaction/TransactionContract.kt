package com.bandonleon.cryptofolio.feature.transaction

import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction
import com.bandonleon.mvp.View

/**
 * TODO: Add description here...
 */
interface TransactionContract {

    interface TransactionView : View {

        fun updateView(transactions: List<Transaction>)

        fun setLoadListener(listener: LoadListener?)

        interface LoadListener {
            fun onLoad()
        }
    }
}