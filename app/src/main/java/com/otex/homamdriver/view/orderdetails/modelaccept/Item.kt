package com.otex.homamdriver.view.orderdetails.modelaccept

data class Item(
    val details: List<Detail>,
    val discount: Int,
    val name: String,
    val note: String,
    val quantity: Int,
    val total_price: Int
)