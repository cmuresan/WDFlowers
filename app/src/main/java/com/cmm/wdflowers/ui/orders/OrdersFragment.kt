package com.cmm.wdflowers.ui.orders

import android.os.Bundle
import androidx.lifecycle.Observer
import com.cmm.wdflowers.R
import com.cmm.wdflowers.databinding.FragmentOrdersBinding
import com.cmm.wdflowers.ui.base.BaseBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel

class OrdersFragment : BaseBindingFragment<FragmentOrdersBinding>() {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    override fun getLayoutId() = R.layout.fragment_orders

    private val ordersViewModel: OrdersViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.viewModel = ordersViewModel
        viewBinding.lifecycleOwner = this

        ordersViewModel.getOrders()
        ordersViewModel.orders().observe(viewLifecycleOwner, Observer {
            //todo populateAdapter
        })
    }
}
