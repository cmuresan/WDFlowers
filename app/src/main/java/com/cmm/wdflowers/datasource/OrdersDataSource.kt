package com.cmm.wdflowers.datasource

import com.cmm.wdflowers.datasource.model.Flower
import retrofit2.Response

interface OrdersDataSource {
    suspend fun getOrder(): Response<List<Flower>>
}
