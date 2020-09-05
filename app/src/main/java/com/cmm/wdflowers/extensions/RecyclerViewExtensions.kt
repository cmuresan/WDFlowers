package com.cmm.wdflowers.extensions

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom [DividerItemDecoration] that allows setting a transparent divider on a [RecyclerView].
 * @param orientation - LayoutManager's orientation and
 * @param dimenResId - The size of the divider.
 */
fun RecyclerView.addSpaceItemDecoration(orientation: Int, @DimenRes dimenResId: Int) {
    addItemDecoration(
        DividerItemDecoration(context, orientation).also { divider ->
            with(ShapeDrawable(RectShape())) {
                intrinsicHeight = (resources.getDimensionPixelSize(dimenResId))
                alpha = 0
                divider.setDrawable(this)
            }
        })
}