package com.bandonleon.cryptofolio.feature.transaction.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.bandonleon.cryptofolio.R
import kotlinx.android.synthetic.main.view_transaction_item.view.*

/**
 * TODO: Add description here...
 */
class TransactionItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_transaction_item, this)
        // labelFrom.text = resources.getString(R.string.transaction_label_from)
        // labelTo.text = resources.getString(R.string.transaction_label_to)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    fun setTransactionType(type: String) {
        textTransactionType.text = type
    }

    fun setTransactionDate(date: String) {
        textDate.text = date
    }

    fun setTransactionFrom(label: String, from: String) {
        labelFrom.text = label
        textFrom.text = from
    }

    fun setTransactionTo(label: String, to: String) {
        labelTo.text = label
        textTo.text = to
    }

    fun setTransactionQuantity(quantity: Float) {
        textTransactionQuantity.text = ""
    }

    fun setTransactionPrice(price: Float, currency: String) {
        textTransactionPrice.text = "0" + currency
    }
}