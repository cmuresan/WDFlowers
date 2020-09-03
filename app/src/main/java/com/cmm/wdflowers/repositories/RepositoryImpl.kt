package com.cmm.wdflowers.repositories

import com.cmm.wdflowers.datasource.OrdersDataSource
import com.cmm.wdflowers.datasource.model.Order
import com.cmm.wdflowers.datasource.model.Resource
import com.cmm.wdflowers.datasource.model.Status

class RepositoryImpl(private val ordersDataSource: OrdersDataSource) : Repository {

    private val currentOrder = mutableListOf<Order>()

    override suspend fun getOrders(): Resource<List<Order>> {
        return ordersDataSource.getOrders().let { response ->
            if (response.isSuccessful) {
                response.body()?.let { orders ->
                    currentOrder.addAll(orders)
                }

                Resource(response.body(), Status.Success)
            } else {
                Resource(null, Status.Error)
            }
        }
    }
}
