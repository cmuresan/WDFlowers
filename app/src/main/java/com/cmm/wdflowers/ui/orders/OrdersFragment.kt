package com.cmm.wdflowers.ui.orders

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmm.wdflowers.R
import com.cmm.wdflowers.databinding.FragmentOrdersBinding
import com.cmm.wdflowers.extensions.addSpaceItemDecoration
import com.cmm.wdflowers.ui.base.BaseBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel

class OrdersFragment : BaseBindingFragment<FragmentOrdersBinding>() {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    override fun getLayoutId() = R.layout.fragment_orders

    private val ordersViewModel: OrdersViewModel by viewModel()
    private var ordersAdapter: OrdersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.viewModel = ordersViewModel
        viewBinding.lifecycleOwner = this

        ordersViewModel.getOrders()

        setupAdapter()
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ordersViewModel.orders().observe(viewLifecycleOwner, Observer { orders ->
            ordersAdapter?.setItems(orders)
        })

        ordersViewModel.navigation().observe(viewLifecycleOwner, Observer {

        })
    }

    private fun setupAdapter() {
        ordersAdapter = OrdersAdapter { orderId ->
            ordersViewModel.navigateToOrderDetails(orderId)
        }
    }

    private fun setupRecyclerView() {
        viewBinding.ordersRV.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ordersAdapter

            addSpaceItemDecoration(RecyclerView.VERTICAL, R.dimen.margin_16dp)
        }
    }
}
