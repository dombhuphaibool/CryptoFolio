package com.bandonleon.cryptofolio.feature.transaction.view

import android.support.v4.widget.SwipeRefreshLayout
import com.bandonleon.cryptofolio.R
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction
import com.bandonleon.cryptofolio.feature.transaction.TransactionContract
import com.bandonleon.cryptofolio.feature.transaction.presenter.TransactionPresenter
import com.bandonleon.cryptofolio.framework.recycler.RecyclerFragment
import com.bandonleon.mvp.Presenter

/**
 * TODO: Add description here...
 */
class TransactionFragment : RecyclerFragment<TransactionAdapter>(), TransactionContract.TransactionView {

    override fun getLayoutProvider(): LayoutProvider {
        return LayoutProvider(R.layout.fragment_transaction, R.id.swipe_refresh, R.id.recycler_view)
    }

    override fun createAdapter(): TransactionAdapter {
        return TransactionAdapter()
    }

    override fun createPresenter(): Presenter {
        return TransactionPresenter()
    }

    override fun setLoadListener(listener: TransactionContract.TransactionView.LoadListener?) {
        setOnRefreshListener(
                if (listener != null) {
                    object: SwipeRefreshLayout.OnRefreshListener {
                        override fun onRefresh() {
                            listener.onLoad()
                        }
                    }
                } else {
                    null
                }
        )
    }

    override fun updateView(transactions: List<Transaction>) {
        adapter.updateTransactions(transactions)
    }

    companion object {
        fun newInstance(): TransactionFragment = TransactionFragment()
    }
}