package com.cmm.wdflowers.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cmm.wdflowers.datasource.model.Order
import com.cmm.wdflowers.datasource.model.Status
import com.cmm.wdflowers.repositories.Repository
import com.cmm.wdflowers.ui.base.BaseViewModel
import com.cmm.wdflowers.ui.orders.model.OrderUiModel
import kotlinx.coroutines.launch

class OrdersViewModel(private val repository: Repository) : BaseViewModel() {

    private val orderLiveData = MutableLiveData<List<OrderUiModel>>()

    fun orders(): LiveData<List<OrderUiModel>> = orderLiveData

    fun getOrders() {
        setLoadingState()
        viewModelScope.launch {
            repository.getOrders().let { resource ->
                when (resource.status) {
                    Status.Success -> {
                        val orders = resource.payload?.mapNotNull { it.toUiModel() }
                        if (orders.isNullOrEmpty()) {
                            setEmptyState()
                        } else {
                            setContentState()
                        }
                        orderLiveData.postValue(orders ?: emptyList())
                    }
                    Status.Error -> {
                        setEmptyState()//todo more like error
                    }
                }
            }
        }
    }
}

private fun Order.toUiModel() = when {
    id == null -> null
    description == null -> null
    price == null -> null
    deliverTo == null -> null
    else -> OrderUiModel(id, description, price, deliverTo)
}
