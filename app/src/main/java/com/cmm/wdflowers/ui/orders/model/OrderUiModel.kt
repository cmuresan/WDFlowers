package com.cmm.wdflowers.ui.orders.model

data class OrderUiModel(
    val id: Int,
    val description: String,
    val price: Int,
    val deliverTo: String
)