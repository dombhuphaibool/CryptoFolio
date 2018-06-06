package com.bandonleon.cryptofolio.framework.recycler

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * TODO: Add description here...
 */
class SpacingItemDecoration(private val verticalSpacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        with(outRect) {
            top = verticalSpacing
            bottom = verticalSpacing
        }
    }
}