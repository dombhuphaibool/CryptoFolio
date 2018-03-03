package com.bandonleon.cryptofolio.framework.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * A singleton to help deal with currencies
 */
object NumberUtils {

    val defaultLocale = Locale.getDefault()

    fun formatAsCurrency(value: Float, locale: Locale = defaultLocale): String {
        val formatter = NumberFormat.getCurrencyInstance(locale)
        return formatter.format(value)
    }

    fun formatAsPercentageChange(value: Float): String {
        val pctFormat =  " %.2f"
        val prefix = when {
            value < 0 -> "-"
            value > 0 -> "+"
            else -> " "
        }
        return prefix + pctFormat.format(value) + "%"
    }

    fun formatAsAssetQuantity(value: Float): String {
        return "%.5f".format(value)
    }
}
