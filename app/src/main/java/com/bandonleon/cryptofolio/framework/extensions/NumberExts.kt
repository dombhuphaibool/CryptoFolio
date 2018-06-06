package com.bandonleon.cryptofolio.framework.extensions

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.absoluteValue

/**
 * A singleton to help deal with currencies
 */
val defaultLocale by lazy {
    Locale.getDefault()
}

fun Float.formatAsCurrency(locale: Locale = defaultLocale): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(this)
}

fun Float.formatAsPercentageChange(): String {
    val pctFormat =  " %.2f"
    val prefix = when {
        this < 0 -> "-"
        this > 0 -> "+"
        else -> " "
    }
    return prefix + pctFormat.format(this.absoluteValue) + "%"
}

fun Float.formatAsAssetQuantity(): String {
    return "%.5f".format(this)
}
