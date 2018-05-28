package com.bandonleon.cryptofolio.feature.portfolio.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bandonleon.cryptofolio.R
import com.bandonleon.cryptofolio.framework.extensions.formatAsAssetQuantity
import com.bandonleon.cryptofolio.framework.extensions.formatAsCurrency
import com.bandonleon.cryptofolio.framework.extensions.formatAsPercentageChange
import org.jetbrains.anko.*

/**
 * Created by dombhuphaibool on 3/1/18.
 */
class PortfolioItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    private val coinIcon: ImageView
    private val coinName: TextView
    private val coinPrice: TextView
    private val coinChange: TextView
    private val assetQuantity: TextView
    private val assetValue: TextView

    init {
        inflate(context, R.layout.view_portfolio_item, this)
        coinIcon = findViewById(R.id.iconView)
        coinName = findViewById(R.id.coinName)
        coinPrice = findViewById(R.id.coinPrice)
        coinChange = findViewById(R.id.coinChange)
        assetQuantity = findViewById(R.id.assetQuantity)
        assetValue = findViewById(R.id.assetValue)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    }

    fun setCoinName(name: String, symbol: String) {
        val resId = resources.getIdentifier(symbol, "drawable", "com.bandonleon.cryptofolio")
        coinIcon.setImageResource(resId)
        coinName.text = name
    }

    fun setCoinPrice(price: Float) {
        coinPrice.text = price.formatAsCurrency()
    }

    fun setCoinChange(percentChanged: Float) {
        with (coinChange) {
            text = percentChanged.formatAsPercentageChange()
            when {
                percentChanged < 0f -> textColor = android.graphics.Color.RED
                percentChanged > 0f -> textColor = android.graphics.Color.GREEN
            }
        }

    }

    fun setAssetQuantity(amount: Float, unit: String) {
        assetQuantity.text = "${amount.formatAsAssetQuantity()} $unit"
    }

    fun setAssetValue(value: Float) {
        assetValue.text = value.formatAsCurrency()
    }
}
