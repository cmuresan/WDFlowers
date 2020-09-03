package com.cmm.wdflowers.repositories

import com.cmm.wdflowers.datasource.OrdersDataSource
import com.cmm.wdflowers.datasource.model.Flower
import com.cmm.wdflowers.datasource.model.Resource
import com.cmm.wdflowers.datasource.model.Status

class RepositoryImpl(private val ordersDataSource: OrdersDataSource) : Repository {

    private val currentOrder = mutableListOf<Flower>()

    override suspend fun getOrder(): Resource<List<Flower>> {
        return ordersDataSource.getOrder().let {
            if (it.isSuccessful) {
                it.body()?.let {
                    currentOrder.addAll(it)
                }

                Resource(it.body(), Status.Success)
            } else {
                Resource(null, Status.Error)
            }
        }
    }
}
