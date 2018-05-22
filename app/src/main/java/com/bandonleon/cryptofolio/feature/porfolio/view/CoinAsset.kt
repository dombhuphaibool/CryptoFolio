package com.bandonleon.cryptofolio.feature.porfolio.view

/**
 * ViewModel used to display coin info along with assets info
 */
data class CoinAsset(
        val coinName: String,
        val amount: Float,
        val unit: String,
        val price: Float,
        val percentChanged1h: Float,
        val percentChanged24h: Float,
        val percentChanged7d: Float)
