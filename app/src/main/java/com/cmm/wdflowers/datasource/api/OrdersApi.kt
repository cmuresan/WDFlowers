package com.cmm.wdflowers.datasource.api

import com.cmm.wdflowers.datasource.model.Flower
import retrofit2.Response
import retrofit2.http.GET

interface OrdersApi {

    @GET("order/")
    suspend fun getOrder(): Response<List<Flower>>

}
