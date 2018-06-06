package com.bandonleon.cryptofolio.feature.transaction.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction
import com.bandonleon.cryptofolio.feature.portfolio.model.Transaction.Type.PURCHASE

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
    private var totalAsset: Float = 0f
    private var amountInvested: Float = 0f
    private var currentValue: Float = 0f

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
                val summaryView = holder.itemView as TransactionSummaryView
                with (summaryView) {
                    setTotalAsset(totalAsset)
                    setAmountInvested(amountInvested)
                    setCurrentValue(currentValue)
                }
            }

            TRANSACTION_ITEM -> {
                val transactionView = holder.itemView as TransactionItemView
                val transaction = transactions[position - 1]
                with (transactionView) {
                    setTransactionType(transaction.transactionType.toString())
                    setTransactionDate(transaction.date)
                    setTransactionFrom("From: ", transaction.exchangeFromId.toString())
                    setTransactionTo("To: ", transaction.exchangeToId.toString())
                    setTransactionQuantity(transaction.coinQuantity)
                    setTransactionPrice(transaction.amount, transaction.currencyId)
                }
            }
        }
    }

    // TODO: Use https://developer.android.com/reference/android/support/v7/recyclerview/extensions/AsyncListDiffer.html
    fun updateTransactions(newTransactions: List<Transaction>) {
        transactions.clear()
        transactions.addAll(newTransactions)

        for (transaction in transactions) {
            if (transaction.transactionType == PURCHASE.ordinal) {
                totalAsset += transaction.coinQuantity
                amountInvested += transaction.amount
                currentValue += (transaction.coinQuantity * 10000f)
            }
        }
        notifyDataSetChanged()
    }
}