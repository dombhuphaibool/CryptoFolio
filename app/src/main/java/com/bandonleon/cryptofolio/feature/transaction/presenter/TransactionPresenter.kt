package com.bandonleon.cryptofolio.feature.transaction.presenter

import com.bandonleon.cryptofolio.feature.transaction.TransactionContract
import com.bandonleon.mvp.BasePresenter

/**
 * TODO: Add description here...
 */
class TransactionPresenter : BasePresenter<TransactionContract.TransactionView>(), TransactionContract.TransactionView.LoadListener {

    override fun onLoad() {

    }
}