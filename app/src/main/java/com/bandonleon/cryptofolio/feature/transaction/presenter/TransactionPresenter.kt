package com.bandonleon.cryptofolio.feature.transaction.presenter

import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Exchange.BANK_WELLS_FARGO
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Exchange.BINANCE
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Exchange.COINBASE
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Exchange.WALLET
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Type.PURCHASE
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Type.RECEIVED
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Type.SENT
import com.bandonleon.cryptofolio.feature.transaction.TransactionContract
import com.bandonleon.mvp.BasePresenter

/**
 * TODO: Add description here...
 */
class TransactionPresenter : BasePresenter<TransactionContract.TransactionView>(), TransactionContract.TransactionView.LoadListener {

    val transactions = ArrayList<Transaction>()

    override fun onBind(view: TransactionContract.TransactionView) {
        super.onBind(view)
        view.setLoadListener(this)
        loadTransactions()
    }

    override fun onUnbind() {
        view.setLoadListener(null)
        super.onUnbind()
    }

    override fun onLoad() {
        loadTransactions()
    }

    private fun loadTransactions() {
        transactions.add(Transaction("2018-04-12", PURCHASE.ordinal, BANK_WELLS_FARGO.ordinal, COINBASE.ordinal, "BTC", 2.5f, 2000f, "USD"))
        transactions.add(Transaction("2018-04-14", RECEIVED.ordinal, BINANCE.ordinal, COINBASE.ordinal, "BTC", 0.5f, 0f, ""))
        transactions.add(Transaction("2018-04-12", SENT.ordinal, COINBASE.ordinal, WALLET.ordinal, "BTC", 3.0f, 0f, ""))
        onTransactionsLoaded()
    }

    private fun onTransactionsLoaded() {
        view.updateView(transactions)
    }
}