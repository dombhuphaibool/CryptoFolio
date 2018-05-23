package com.bandonleon.cryptofolio.feature.portfolio.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import com.bandonleon.cryptofolio.R
import com.bandonleon.cryptofolio.framework.utils.NumberUtils
import org.jetbrains.anko.bottomPadding
import org.jetbrains.anko.leftPadding
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textView

/**
 * An item view that shows the Portfolio summary
 */
class PortfolioSummaryView(context: Context) : LinearLayout(context) {

    val portfolioValue: TextView
    lateinit var initialInvestment: TextView
    lateinit var investmentChange: TextView

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        orientation = VERTICAL

        portfolioValue = textView {
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            bottomPadding = 22
            gravity = Gravity.CENTER
            textSize = 36f
            typeface = Typeface.DEFAULT_BOLD
        }

        linearLayout {
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)

            with (textView(R.string.initial_investment)) {
                lparams {
                    width = WRAP_CONTENT
                    height = WRAP_CONTENT
                    weight = 2f
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                textSize = 20f
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }

            initialInvestment = textView().apply {
                lparams {
                    width = WRAP_CONTENT
                    height = WRAP_CONTENT
                    weight = 2f
                    gravity = Gravity.START
                }
                textSize = 20f
                leftPadding = resources.getDimensionPixelOffset(R.dimen.text_label_end_padding)
            }
            investmentChange = textView().apply {
                lparams {
                    width = WRAP_CONTENT
                    height = WRAP_CONTENT
                    weight = 1f
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                textSize = 20f
            }
        }
    }

    fun setPortfolioSummary(totalValue: Float, initialValue: Float) {
        portfolioValue.text = NumberUtils.formatAsCurrency(totalValue)
        initialInvestment.text = NumberUtils.formatAsCurrency(initialValue)
        val percentageChange = (totalValue - initialValue) / initialValue
        with (investmentChange) {
            text = NumberUtils.formatAsPercentageChange(percentageChange)
            when {
                percentageChange < 0f -> textColor = Color.RED
                percentageChange > 0f -> textColor = Color.GREEN
            }
        }
    }
}
