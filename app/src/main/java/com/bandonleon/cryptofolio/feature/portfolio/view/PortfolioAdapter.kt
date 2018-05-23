package com.bandonleon.cryptofolio.feature.portfolio.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bandonleon.cryptofolio.framework.utils.NumberUtils

class PortfolioAdapter : RecyclerView.Adapter<PortfolioAdapter.PorfolioViewHolder>() {

    companion object {
        const val PORFOLIO_SUMMARY = 0
        const val PORFOLIO_ASSET = 1
    }

    sealed class PorfolioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        class PorfolioSummaryViewHolder(private val portfolioSummaryView: PortfolioSummaryView) : PorfolioViewHolder(portfolioSummaryView) {
            fun setPortfolioSummary(totalValue: Float, initialValue: Float) {
                portfolioSummaryView.setPortfolioSummary(totalValue, initialValue)
            }
        }

        class PorfolioAssetViewHolder(private val portfolioItemView: PortfolioItemView) : PorfolioViewHolder(portfolioItemView) {

            fun setCoinName(coinName: String, symbol: String) {
                val resId = portfolioItemView.resources.getIdentifier(symbol, "drawable", "com.bandonleon.cryptofolio")
                // portfolioItemView.coinIcon.setImageResource(resId)
                portfolioItemView.coinName.text = coinName
            }

            fun setPrice(price: Float) {
                portfolioItemView.priceUsd.text = NumberUtils.formatAsCurrency(price)
            }

            fun setAmount(amount: Float, unit: String) {
                portfolioItemView.amount.text = "${NumberUtils.formatAsAssetQuantity(amount)} $unit"
            }
        }
    }

    private var coinAssets: MutableList<CoinAsset> = ArrayList()

    override fun getItemCount(): Int {
        return coinAssets.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) PORFOLIO_SUMMARY else PORFOLIO_ASSET
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PorfolioViewHolder {
        return when (viewType) {
            PORFOLIO_SUMMARY -> PorfolioViewHolder.PorfolioSummaryViewHolder(PortfolioSummaryView(parent.context))
            PORFOLIO_ASSET -> PorfolioViewHolder.PorfolioAssetViewHolder(PortfolioItemView(parent.context))
            else -> PorfolioViewHolder.PorfolioAssetViewHolder(PortfolioItemView(parent.context))
        }
    }

    override fun onBindViewHolder(holder: PorfolioViewHolder, position: Int) {
        when (holder.itemViewType) {
            PORFOLIO_SUMMARY -> {
                val summaryHolder = holder as PorfolioViewHolder.PorfolioSummaryViewHolder
                summaryHolder.setPortfolioSummary((coinAssets.sumByDouble { it.amount * it.price.toDouble() }).toFloat(), 1f)
            }

            PORFOLIO_ASSET -> {
                val assetHolder = holder as PorfolioViewHolder.PorfolioAssetViewHolder
                with(coinAssets.get(position - 1)) {
                    assetHolder.setCoinName(coinName, unit.toLowerCase())
                    assetHolder.setPrice(price)
                    assetHolder.setAmount(amount, unit)
                }
            }
        }
    }

    // TODO: Use https://developer.android.com/reference/android/support/v7/recyclerview/extensions/AsyncListDiffer.html
    fun updateCoinAssets(newCoinAssets: List<CoinAsset>) {
        coinAssets.clear()
        coinAssets.addAll(newCoinAssets)
        notifyDataSetChanged()
    }
}
