package com.bandonleon.cryptofolio.feature.porfolio.view

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bandonleon.cryptofolio.R
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageView
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalPadding

/**
 * Created by dombhuphaibool on 3/1/18.
 */
class PorfolioItemView(context: Context) : LinearLayout(context) {

    lateinit var coinName: TextView
    // lateinit var coinIcon: ImageView
    val priceUsd: TextView
    val amount: TextView

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        orientation = HORIZONTAL
        weightSum = 3f
        verticalPadding = dip(R.dimen.portfolio_item_vpadding)

        linearLayout {
            lparams {
                width = WRAP_CONTENT
                height = WRAP_CONTENT
                orientation = HORIZONTAL
                weight = 1f
            }
/*
            coinIcon = imageView(R.drawable.btc).apply {
                lparams {
                    width = WRAP_CONTENT // dip(R.dimen.portfolio_icon_size)
                    height = WRAP_CONTENT // dip(R.dimen.portfolio_icon_size)
                    // gravity = Gravity.CENTER
                }
            }
*/
            coinName = textView("testCoin").apply {
                lparams {
                    width = WRAP_CONTENT
                    height = WRAP_CONTENT
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }

        priceUsd = textView("$25.45") {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
        }

        amount = textView("22.382") {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }
}
