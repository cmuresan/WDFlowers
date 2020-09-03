package com.cmm.wdflowers.datasource.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("price") val price: Int? = null,
    @SerializedName("deliver_to") val deliverTo: String? = null
)