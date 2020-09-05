package com.cmm.wdflowers.datasource

import com.cmm.wdflowers.datasource.model.Order
import retrofit2.Response

interface OrdersDataSource {
    suspend fun getOrders(): Response<List<Order>>
}
