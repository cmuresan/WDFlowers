package com.cmm.wdflowers.repositories

import android.util.Log
import com.cmm.wdflowers.R
import com.cmm.wdflowers.datasource.OrdersDataSource
import com.cmm.wdflowers.datasource.model.Order
import com.cmm.wdflowers.datasource.model.Resource
import com.cmm.wdflowers.datasource.model.Status
import com.cmm.wdflowers.utils.safeApiCall
import java.io.IOException

const val TAG = "RepositoryImpl"

class RepositoryImpl(private val ordersDataSource: OrdersDataSource) : Repository {

    private val orders = mutableListOf<Order>()

    override suspend fun getOrders(): Resource<List<Order>> {
        return try {
            safeApiCall { ordersDataSource.getOrders() }.let { response ->
                if (response.isSuccessful) {
                    response.body()?.let { orders ->
                        this.orders.addAll(orders)
                    }

                    Resource(response.body(), Status.Success)
                } else {
                    Resource(null, Status.Error(R.string.generic_error))
                }
            }
        } catch (ioe: IOException) {
            Log.e(TAG, "getOrders: ", ioe)
            Resource(null, Status.Error(R.string.no_internet_connection))
        }
    }

    override fun getOrderById(orderId: Int) = orders.firstOrNull { it.id == orderId }

}
