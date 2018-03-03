package com.bandonleon.cryptofolio.feature.porfolio.view

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.bandonleon.cryptofolio.R
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalPadding

/**
 * Created by dombhuphaibool on 3/1/18.
 */
class PorfolioItemView(context: Context) : LinearLayout(context) {

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        orientation = HORIZONTAL
        weightSum = 3f
        verticalPadding = context.resources.getDimensionPixelOffset(R.dimen.PORFOLIO_ITEM_VPADDING)
    }

    val coinName = textView("testCoin") {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
        gravity = Gravity.CENTER_HORIZONTAL
    }
    val priceUsd = textView("$25.45") {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
        gravity = Gravity.CENTER_HORIZONTAL
    }
    val amount = textView("22.382") {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
        gravity = Gravity.CENTER_HORIZONTAL
    }
}
