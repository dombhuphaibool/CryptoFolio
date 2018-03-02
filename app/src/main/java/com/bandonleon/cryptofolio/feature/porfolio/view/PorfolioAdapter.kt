package com.bandonleon.cryptofolio.feature.porfolio.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class PorfolioAdapter : RecyclerView.Adapter<PorfolioAdapter.PorfolioViewHolder>() {

    companion object {
        const val PORFOLIO_HEADER = 0
        const val PORFOLIO_ASSET = 1
    }

    sealed class PorfolioViewHolder(open protected val porfolioItemView: PorfolioItemView) : RecyclerView.ViewHolder(porfolioItemView) {
        class PorfolioHeaderViewHolder(override val porfolioItemView: PorfolioItemView) : PorfolioViewHolder(porfolioItemView) {
            fun setCoinName(label: String) {
                porfolioItemView.coinName.text = label
            }

            fun setPrice(label: String) {
                porfolioItemView.priceUsd.text = label
            }

            fun setAmount(label: String) {
                porfolioItemView.amount.text = label
            }
        }

        class PorfolioAssetViewHolder(override val porfolioItemView: PorfolioItemView) : PorfolioViewHolder(porfolioItemView) {

            fun setCoinName(coinName: String) {
                porfolioItemView.coinName.text = coinName
            }

            fun setPrice(price: Float) {
                porfolioItemView.priceUsd.text = "$%.2f".format(price)
            }

            fun setAmount(amount: Float) {
                porfolioItemView.amount.text = "%.5f".format(amount)
            }
        }
    }

    private var coinAssets: MutableList<CoinAsset> = ArrayList<CoinAsset>()

    override fun getItemCount(): Int {
        return coinAssets.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) PORFOLIO_HEADER else PORFOLIO_ASSET
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PorfolioViewHolder {
        val porfolioItemView = PorfolioItemView(parent.context)
        return when (viewType) {
            PORFOLIO_HEADER -> PorfolioViewHolder.PorfolioHeaderViewHolder(porfolioItemView)
            PORFOLIO_ASSET -> PorfolioViewHolder.PorfolioAssetViewHolder(porfolioItemView)
            else -> PorfolioViewHolder.PorfolioAssetViewHolder(porfolioItemView)
        }
    }

    override fun onBindViewHolder(holder: PorfolioViewHolder, position: Int) {
        when (holder.itemViewType) {
            PORFOLIO_HEADER -> {
                val headerHolder = holder as PorfolioViewHolder.PorfolioHeaderViewHolder
                headerHolder.setCoinName("COIN")
                headerHolder.setPrice("PRICE")
                headerHolder.setAmount("AMOUNT")
            }

            PORFOLIO_ASSET -> {
                val assetHolder = holder as PorfolioViewHolder.PorfolioAssetViewHolder
                with(coinAssets.get(position - 1)) {
                    assetHolder.setCoinName(coinName)
                    assetHolder.setPrice(price)
                    assetHolder.setAmount(amount)
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