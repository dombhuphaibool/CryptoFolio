package com.bandonleon.cryptofolio.feature.transaction.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.bandonleon.cryptofolio.R

/**
 * TODO: Add description here...
 */
class TransactionSummaryView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_transaction_summary, this)

    }


}