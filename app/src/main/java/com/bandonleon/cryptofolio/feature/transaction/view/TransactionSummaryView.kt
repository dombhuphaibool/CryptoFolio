package com.bandonleon.cryptofolio.feature.transaction.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.bandonleon.cryptofolio.R
import com.bandonleon.cryptofolio.framework.extensions.formatAsAssetQuantity
import com.bandonleon.cryptofolio.framework.extensions.formatAsCurrency
import kotlinx.android.synthetic.main.view_transaction_summary.view.*

/**
 * TODO: Add description here...
 */
class TransactionSummaryView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_transaction_summary, this)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    fun setTotalAsset(totalAsset: Float) {
        textTotalAsset.text = totalAsset.formatAsAssetQuantity()
    }

    fun setAmountInvested(amountInvested: Float) {
        textAmountInvested.text = amountInvested.formatAsCurrency()
    }

    fun setCurrentValue(currentValue: Float) {
        textCurrentValue.text = currentValue.formatAsCurrency()
    }
}