package com.cmm.wdflowers.repositories

import com.cmm.wdflowers.datasource.model.Order
import com.cmm.wdflowers.datasource.model.Resource

interface Repository {
    suspend fun getOrders(): Resource<List<Order>>

    fun getOrderById(orderId: Int): Order?
}
