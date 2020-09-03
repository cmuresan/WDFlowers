package com.cmm.wdflowers.datasource

import com.cmm.wdflowers.datasource.api.OrdersApi
import com.cmm.wdflowers.datasource.model.Order
import retrofit2.Response

class OrdersDataSourceImpl(private val ordersApi: OrdersApi) : OrdersDataSource {
    override suspend fun getOrders(): Response<List<Order>> = ordersApi.getOrders()
}
