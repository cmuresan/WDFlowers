package com.cmm.wdflowers.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<Model, ViewHolder : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<ViewHolder>() {
    abstract fun setItems(items: List<Model>)
}