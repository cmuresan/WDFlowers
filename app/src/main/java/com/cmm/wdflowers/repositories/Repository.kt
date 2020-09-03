package com.cmm.wdflowers.repositories

import com.cmm.wdflowers.datasource.model.Flower
import com.cmm.wdflowers.datasource.model.Resource

interface Repository {
    suspend fun getOrder(): Resource<List<Flower>>
}
