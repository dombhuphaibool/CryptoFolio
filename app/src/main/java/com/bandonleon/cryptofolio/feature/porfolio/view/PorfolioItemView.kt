package com.bandonleon.cryptofolio.feature.porfolio.view

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import com.bandonleon.cryptofolio.R
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalPadding

/**
 * Created by dombhuphaibool on 3/1/18.
 */
class PorfolioItemView(private val ctx: Context) : LinearLayout(ctx) {

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        orientation = HORIZONTAL
        weightSum = 3f
        verticalPadding = context.resources.getDimensionPixelOffset(R.dimen.PORFOLIO_ITEM_VPADDING)
    }

    val coinName = textView("testCoin") {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
    }
    val priceUsd = textView("$25.45") {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
    }
    val amount = textView("22.382") {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
    }
}