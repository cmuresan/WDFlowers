package com.cmm.wdflowers.ui.orders.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.cmm.wdflowers.R
import com.cmm.wdflowers.databinding.FragmentOrderDetailsBinding
import com.cmm.wdflowers.ui.base.BaseBindingFragment
import com.cmm.wdflowers.ui.orders.OrdersViewModel
import com.cmm.wdflowers.ui.orders.model.OrderDetailsUiModel
import com.cmm.wdflowers.ui.orders.model.OrderUiModel
import org.koin.android.viewmodel.ext.android.viewModel

const val ORDER_ID = "order_id"

class OrderDetailsFragment : BaseBindingFragment<FragmentOrderDetailsBinding>() {

    override fun getLayoutId() = R.layout.fragment_order_details

    private val ordersViewModel: OrdersViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val orderId = it.getInt(ORDER_ID, -1)
            ordersViewModel.getOrder(orderId)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ordersViewModel.orderById().observe(viewLifecycleOwner, Observer { orderUiModel ->
            viewBinding.orderDetailsUiModel = getOrderDetailsUiModel(orderUiModel)
        })
    }

    private fun getOrderDetailsUiModel(orderUiModel: OrderUiModel): OrderDetailsUiModel {
        return OrderDetailsUiModel(
            String.format(getString(R.string.product_name), orderUiModel.description),
            String.format(getString(R.string.product_price), orderUiModel.price),
            String.format(getString(R.string.product_destination), orderUiModel.deliverTo)
        )
    }
}