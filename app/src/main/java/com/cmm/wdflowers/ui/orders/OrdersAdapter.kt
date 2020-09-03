package com.cmm.wdflowers.ui.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cmm.wdflowers.databinding.ItemOrderBinding
import com.cmm.wdflowers.ui.base.BaseRecyclerAdapter
import com.cmm.wdflowers.ui.base.BaseViewHolder
import com.cmm.wdflowers.ui.orders.model.OrderUiModel

class OrdersAdapter(
    private val clickListener: (id: Int) -> Unit
) : BaseRecyclerAdapter<OrderUiModel, OrdersAdapter.OrderViewHolder>() {

    private var data: MutableList<OrderUiModel> = mutableListOf()

    override fun setItems(items: List<OrderUiModel>) {
        if (data == items) return
        data = items.toMutableList()
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        val binding = ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrderViewHolder(binding, clickListener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class OrderViewHolder(
        private val binding: ItemOrderBinding,
        private val clickListener: (id: Int) -> Unit
    ) : BaseViewHolder<OrderUiModel>(binding.root) {

        override fun bind(item: OrderUiModel) {
            binding.orderUiModel = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                clickListener.invoke(item.id)
            }
        }
    }
}