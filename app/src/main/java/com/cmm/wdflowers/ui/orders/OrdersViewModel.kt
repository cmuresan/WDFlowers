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

    private val ordersLiveData = MutableLiveData<List<OrderUiModel>>()
    private val orderByIdLiveData = MutableLiveData<OrderUiModel>()

    fun orders(): LiveData<List<OrderUiModel>> = ordersLiveData
    fun orderById(): LiveData<OrderUiModel> = orderByIdLiveData

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
                        ordersLiveData.postValue(orders ?: emptyList())
                    }
                    Status.Error -> {
                        setEmptyState()//todo more like error
                    }
                }
            }
        }
    }

    fun getOrder(orderId: Int) {
        repository.getOrderById(orderId)?.toUiModel()?.let {
            orderByIdLiveData.postValue(it)
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
