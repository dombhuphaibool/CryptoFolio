package com.bandonleon.cryptofolio.feature.transaction.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction

/**
 * TODO: Add description here...
 */
class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    companion object {
        const val TRANSACTION_SUMMARY = 0
        const val TRANSACTION_ITEM = 1
    }

    sealed class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        class TransactionSummaryViewHolder(transactionSummaryView: TransactionSummaryView) : TransactionViewHolder(transactionSummaryView)
        class TransactionItemViewHolder(transactionItemView: TransactionItemView) : TransactionViewHolder(transactionItemView)
    }

    private var transactions: MutableList<Transaction> = ArrayList()

    override fun getItemCount(): Int {
        return transactions.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TRANSACTION_SUMMARY else TRANSACTION_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return when (viewType) {
            TRANSACTION_SUMMARY -> TransactionViewHolder.TransactionSummaryViewHolder(TransactionSummaryView(parent.context))
            TRANSACTION_ITEM -> TransactionViewHolder.TransactionItemViewHolder(TransactionItemView(parent.context))
            else -> TransactionViewHolder.TransactionItemViewHolder(TransactionItemView(parent.context))
        }
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        when (holder.itemViewType) {
            TRANSACTION_SUMMARY -> {

            }

            TRANSACTION_ITEM -> {

            }
        }
    }
}